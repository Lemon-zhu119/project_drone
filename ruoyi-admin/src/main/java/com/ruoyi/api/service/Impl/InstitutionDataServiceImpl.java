package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.InstitutionData;
import com.ruoyi.api.domain.dto.CustomerRegisterDto;
import com.ruoyi.api.domain.dto.InstitutionDateDto;
import com.ruoyi.api.domain.dto.QualificationDto;
import com.ruoyi.api.mapper.InstitutionDataMapper;
import com.ruoyi.api.service.InstitutionDataService;
import com.ruoyi.api.util.FileUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.customer.domain.Customer;
import com.ruoyi.web.controller.customer.mapper.CustomerMapper;
import com.ruoyi.web.controller.institutionmore.domain.Institution;
import com.ruoyi.web.controller.institutionmore.mapper.InstitutionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InstitutionDataServiceImpl implements InstitutionDataService {
    @Autowired
    private InstitutionDataMapper institutionDataMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private InstitutionMapper institutionMapper;

    private final Logger logger= LoggerFactory.getLogger(InstitutionDataServiceImpl.class);


    @Override
    public List<InstitutionDateDto> getInstitutionList(Integer sortType, String city,
                                                       String subject, Double latitude, Double longitude, String keyword) {

        InstitutionQueryParam queryParam = new InstitutionQueryParam()
                .setCity(city)
                .setSubject(subject)
                .setKeyword(keyword);

        List<InstitutionData> list = institutionDataMapper.selectListByConditions(queryParam);

        // 处理标签
        for (InstitutionData institutionData : list) {
            List<String> Tags = new ArrayList<>();
            Optional.ofNullable(institutionData.getInsTag())
                    .ifPresent(insTag -> Tags.addAll(Arrays.asList(insTag.split(";"))));
            institutionData.setTags(Tags);
        }

        // 处理排序
        switch (sortType) {
            case 1:
                return list.stream().distinct().map(ins->{
                    InstitutionDateDto dto=convertToDto(ins);
                    if(latitude!=null&&longitude!=null&&dto.getLatitude()!=null&&dto.getLongitude()!=null){
                        Double distance=calculateHaversineDistance(latitude,longitude,dto.getLatitude(),dto.getLongitude());
                        dto.setDistance(distance);
                    }
                    return dto;
                        }).sorted(Comparator.comparing(InstitutionData::getId))
                        .collect(Collectors.toList());
            case 2:
//                 return list.stream().map(ins->{
//                    InstitutionDateDto dto=convertToDto(ins);
//                    if(latitude!=null&&longitude!=null&&dto.getLatitude()!=null&&dto.getLongitude()!=null){
//                        Double distance=calculateHaversineDistance(latitude,longitude,dto.getLatitude(),dto.getLongitude());
//                        dto.setDistance(distance);
//                    }
//                    return dto;
//                }).sorted(Comparator.comparing(InstitutionData::getScore,Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
                List<InstitutionDateDto> list1=list.stream().map(this::convertToDto).collect(Collectors.toList());
                  return processDistanceAndSort(list1,latitude,longitude).stream().sorted(Comparator.comparing(InstitutionData::getScore,Comparator.nullsLast(Comparator.reverseOrder()))).collect(Collectors.toList());
            default:
                return list.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList());
        }
    }
    private InstitutionDateDto convertToDto(InstitutionData data) {
        InstitutionDateDto dto = new InstitutionDateDto();
        BeanUtils.copyProperties(data, dto);
        Map<String,Double> coordinates=institutionDataMapper.getCoordinates(data.getId());
        if(coordinates!=null){
            dto.setLatitude(coordinates.get("latitude"));
            dto.setLongitude(coordinates.get("longitude"));
        }
        // 设置经纬度等额外字段
        // 可以从其他地方获取经纬度信息
        return dto;
    }
    @Override
    public List<InstitutionDateDto> selectListByCity(String city) {
        List<InstitutionDateDto>list= institutionDataMapper.selectListByCity(city);
        for (InstitutionDateDto ins:list){
            processingTags(ins);
        }
       return list;
    }

    @Override
    public List<InstitutionData> selectInsPic() {
        return institutionDataMapper.selectInsPic();
    }

    @Override
    public InstitutionData selectListById(Integer id) {
        InstitutionData institutionData=institutionDataMapper.selectListById(id);
        if(institutionData!=null){
            String tag=institutionData.getInsTag();
            List<String> Tags=new ArrayList<>();
            if (tag != null && !tag.isEmpty()) {
                StringTokenizer tokenizer=new StringTokenizer(tag,";");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken().trim();
                    Tags.add(token);
                }
                institutionData.setTags(Tags);
            }
        }

        return institutionData;
    }

    @Override
    public List<InstitutionDateDto> selectListByName(String name,Double latitude,Double longitude) {
         List<InstitutionDateDto> list=institutionDataMapper.selectListByName(name);
         for (InstitutionDateDto ins:list){
             processingTags(ins);
         }
         return processDistanceAndSort(list,latitude,longitude);

    }

    @Override
    public List<InstitutionDateDto> selectList() {
        return institutionDataMapper.selectList();
    }

    @Override
    public List<InstitutionDateDto> selectListByProvince(String province) {
        if(StringUtils.isEmpty(province)){
            return new ArrayList<>();
        }
        return institutionDataMapper.selectListByProvince(province);
    }

    @Override
    public List<InstitutionDateDto> selectListByDistance(Double latitude, Double longitude, String city) {
        // 1. 获取机构列表
        List<InstitutionDateDto> institutionList;
        if (StringUtils.isNotEmpty(city)) {
            if (isProvinceLevel(city)) {
                String fullProvinceName = getFullProvinceName(city);
                institutionList = institutionDataMapper.selectListByProvince(fullProvinceName);
            } else {
                institutionList = institutionDataMapper.selectListByCity(city);
            }
        } else {
            institutionList = institutionDataMapper.selectList();
        }

        // 2. 处理经纬度和距离
        return processDistanceAndSort(institutionList,longitude,latitude);
    }

    @Override
    public void insertCustomer(CustomerRegisterDto customerRegisterDto) {
        if(customerMapper.checkPhoneExist(customerRegisterDto.getPhone())){
            throw new ServiceException("手机号已存在");
        }
        if(customerMapper.checkUserNameExist(customerRegisterDto.getUsername())){
            throw new ServiceException("用户名已存在");
        }
        Customer customer=new Customer();
        customer.setUsername(customerRegisterDto.getUsername());
        customer.setPhone(customerRegisterDto.getPhone());
        BeanUtils.copyProperties(customer,customerRegisterDto);
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void qualification(QualificationDto qualificationDto) {
//        Customer customer=new Customer();
//        customer.setPhone(qualificationDto.getPhone());
//        List<Customer> customers=customerMapper.selectCustomerList(customer);
//
//        if(customers==null||customers.isEmpty()){
//            throw new ServiceException("用户信息不存在");
//        }
//        Integer customerId= Math.toIntExact(customers.get(0).getId());
//        if(customerId==null){
//            throw new ServiceException("用户id不能为空");
//        }
//        Institution institution=institutionMapper.selectInstitutionByUserId(customerId.intValue());
//        if(institution==null){
//            throw new ServiceException("未找到对应机构信息");
//        }
////   机构和用户绑定,暂时不写     institution.setUserId(customer1.get(0).getId().intValue());
//        if(institution==null){
//            throw new ServiceException("未找到对应机构信息");
//        }
//        if(qualificationDto.getFilesName()!=null||!qualificationDto.getFilesName().isEmpty()){
//            throw new ServiceException("未接受到文件");
//        }
//        try {
//            logger.info("开始文件上传");
//            String fileURl=fileUtil.uploadBase64File(qualificationDto.getFilesName());
//            logger.info("文件上传成功,访问URL",fileURl);
//            Map<String, Object> map=new HashMap<>();
//            map.put("customerId",customers.get(0).getId().intValue());
//            map.put("id",institution.getId());
//            institutionMapper.update(map);
//        }catch (Exception e){
//            logger.error("资质提交失败",e);
//            throw new ServiceException("文件上传失败:"+e.getMessage());
//        }
//    }
//
//    /**
//     * 计算两点之间的距离（km）
//     */


    private Double calculateHaversineDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        // 添加参数检查
        if (lat1 == null || lon1 == null || lat2 == null || lon2 == null) {
            return null;
        }

        double R = 6371; // 地球半径（km）
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }


    /**
     * 设置距离描述
     */


    private boolean isProvinceLevel(String name) {
        Set<String> provinceShortNames = new HashSet<>(Arrays.asList(
                "北京", "上海", "天津", "重庆",
                "新疆", "广西", "内蒙古", "宁夏", "西藏",
                "浙江", "云南", "四川", "贵州", "河南", "河北", "山东", "山西",
                "江苏", "江西", "湖南", "湖北", "广东", "海南", "福建", "台湾",
                "甘肃", "青海", "安徽", "吉林", "辽宁", "黑龙江"
        ));

        return name.endsWith("省") ||
                name.endsWith("自治区") ||
                name.endsWith("市") ||
                provinceShortNames.contains(name);
    }

    // 获取完整省份名称方法（之前的代码）
    private String getFullProvinceName(String shortName) {
        Map<String, String> provinceMap = new HashMap<>();
        provinceMap.put("新疆", "新疆维吾尔自治区");
        provinceMap.put("广西", "广西壮族自治区");
        provinceMap.put("内蒙古", "内蒙古自治区");
        provinceMap.put("宁夏", "宁夏回族自治区");
        provinceMap.put("西藏", "西藏自治区");
        provinceMap.put("北京", "北京市");
        provinceMap.put("上海", "上海市");
        provinceMap.put("天津", "天津市");
        provinceMap.put("重庆", "重庆市");

        if (provinceMap.containsKey(shortName)) {
            return provinceMap.get(shortName);
        }
        return shortName.endsWith("省") ? shortName : shortName + "省";
    }
    private List<InstitutionDateDto> processDistanceAndSort(
            List<InstitutionDateDto> dtoList,
            Double latitude,
            Double longitude) {

        // 添加日志
        logger.info("处理距离排序，输入列表大小: {}", dtoList.size());

        List<InstitutionDateDto> result = dtoList.stream()
                .filter(Objects::nonNull)
                .peek(dto -> {
                    try {
                        if (latitude != null && longitude != null &&
                                dto.getLatitude() != null && dto.getLongitude() != null) {

                            double distance = calculateHaversineDistance(
                                    latitude,
                                    longitude,
                                    dto.getLatitude(),
                                    dto.getLongitude()
                            );
                            dto.setDistance(distance);
                        }
                    } catch (Exception e) {
                        logger.error("计算距离失败: {}", dto.getId(), e);
                    }
                })
                .collect(Collectors.toList());

        // 添加日志
        logger.info("处理完成，输出列表大小: {}", result.size());

        return result;
    }

    private InstitutionDateDto processingTags(InstitutionDateDto institutionDateDto){
        List<String> Tags = new ArrayList<>();
        // 检查insTag是否为null
        Optional.ofNullable(institutionDateDto.getInsTag()).ifPresent(insTag->Tags.addAll(Arrays.asList(insTag.split(";"))));
        institutionDateDto.setTags(Tags);
        return institutionDateDto;
    }




}
