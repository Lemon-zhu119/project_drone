package com.ruoyi.api.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import com.alibaba.fastjson2.filter.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.pagehelper.PageInfo;
import com.ruoyi.api.common.Result;
import com.ruoyi.api.domain.CityDto;
import com.ruoyi.api.domain.Course;
import com.ruoyi.api.domain.Province;
import com.ruoyi.api.domain.dto.CommentDto;
import com.ruoyi.api.domain.InstitutionData;
import com.ruoyi.api.domain.dto.CustomerRegisterDto;
import com.ruoyi.api.domain.dto.InstitutionDateDto;
import com.ruoyi.api.domain.dto.QualificationDto;
import com.ruoyi.api.service.*;
import com.ruoyi.api.util.Maputil;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.customer.domain.Customer;
import com.ruoyi.web.controller.customer.service.impl.CustomerServiceImpl;
import com.ruoyi.web.controller.institutionmore.domain.Institution;
import com.ruoyi.web.controller.institutionmore.service.IInstitutionService;
import com.ruoyi.web.controller.swiper.domain.SwiperPicture;
import com.ruoyi.web.controller.swiper.service.ISwiperPictureService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.ruoyi.common.utils.PageUtils.startPage;

@RestController
@RequestMapping("/api/institution")
public class InstitutionDataController extends BaseController {

    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private IInstitutionService iInstitutionService;
    @Autowired
    private InstitutionDataService institutionDataService;
    @Autowired
    private ISwiperPictureService iSwiperPictureService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private QualificationRecordService qualificationRecordService;


    private SerializeConfig serializeConfig = new SerializeConfig();
    private final Logger logger= LoggerFactory.getLogger(InstitutionDataController.class);

    /**
     *
     * @param sortType
     * @param city
     * @param subject
     * @param latitude
     * @param longitude
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo getList(@RequestParam("sortType") Integer sortType,
                                 @RequestParam (required = false)String city,
                                 @RequestParam (required = false)String subject,
                                 @RequestParam (required = false) Double latitude,
                                 @RequestParam (required = false) Double longitude,
                                 @RequestParam (required = false) String keyword,
                                 @RequestParam(name = "pageNum", defaultValue = "1")Integer pageNum,
                                 @RequestParam(name = "pageSize", defaultValue = "10")Integer pageSize){
        startPage();
        if (longitude == null) {
            // 默认使用温州洞头区的坐标
            longitude = 121.157463;

        }
        if (latitude == null) {
            latitude = 27.836088;
        }
        List<InstitutionDateDto> list=institutionDataService.getInstitutionList(sortType,city,subject,latitude,longitude,keyword);
        List<Map<String,Object>> resultMap=new ArrayList<>();
        SimplePropertyPreFilter filter=new SimplePropertyPreFilter();
        filter.getExcludes().addAll(Arrays.asList("id", "courses", "courseTags", "insTag","tags"));
        for (InstitutionDateDto ins:list){
            String name=Maputil.getNameByLatAndLog(ins.getLatitude(),ins.getLongitude());
            ins.setArea(name);
            Map<String,Object> insmap=new LinkedHashMap<>();
            ins.setCompanyName(processCompanyName(ins.getCompanyName()));
            insmap.put("id",ins.getId());
            insmap.put("insName",ins.getCompanyName());
            insmap.put("insTags",ins.getTags());
            String jsonString=JSON.toJSONString(ins,filter,SerializerFeature.WriteMapNullValue);
            Map<String,Object> map=JSON.parseObject(jsonString,LinkedHashMap.class);
            insmap.put("institution",map);
            ins.setCourses(courseService.selectCourseList(ins.getId()));
            List<Course> courseList=ins.getCourses();
            if(courseList!=null && !courseList.isEmpty()){
                Course course=courseList.get(0);
                if(ins.getCourseTags()==null){
                    ins.setCourseTags(new ArrayList<>());
                }
                // 将构建的字符串添加到tags列表中
                ins.getCourseTags().add(course.getExpense()+"元起" );
                ins.getCourseTags().add(course.getName());
                ins.getCourseTags().add(course.getSubject());
            }
            insmap.put("courses",courseList);
            insmap.put("courseTag",ins.getCourseTags());
            resultMap.add(insmap);
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(resultMap);
        rspData.setTotal(new PageInfo(resultMap).getTotal());
        return rspData;
    }


    @GetMapping("/listbyinsname")
    public TableDataInfo getListByName(@RequestParam String name,
                                       @RequestParam(required = false)Double latitude,
                                       @RequestParam(required = false)Double longitude,
                                       @RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
        startPage();
        if (latitude == null) {
            // 默认使用温州洞头区的坐标

            latitude = 27.836088;
        }
        if (longitude == null) {
            longitude = 121.157463;
        }
        List<Map<String,Object>> resultMap=new ArrayList<>();
        List<InstitutionDateDto> list=institutionDataService.selectListByName(name,latitude,longitude);
        for(InstitutionDateDto ins:list){
            resultMap.add(processInstitutionData(ins));
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(resultMap);
        rspData.setTotal(new PageInfo(resultMap).getTotal());
        return rspData;

    }
    //需要修改传用户id参数获取用户地址解析经纬度计算距离
    @GetMapping("/listbycity")
    private TableDataInfo getListByCity(@RequestParam String cityName,
                                        @RequestParam(required = false)Double latitude,
                                        @RequestParam(required = false)Double longitude,
                                        @RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
        startPage();
        if (latitude == null) {
            latitude = 27.836088;
        }
        if (longitude == null) {
            longitude = 121.157463;
        }
       if(StringUtils.isEmpty(cityName)){
           return getDataTable(Collections.emptyList());
       }
        List<Map<String,Object>> resultMap=new ArrayList<>();
       List<InstitutionDateDto> list;
       try{
           String name=cityName.trim();
           if(inProvinceLevel(name)){
               String ProvinceName=getFullProvinceName(name);
               list=institutionDataService.selectListByProvince(ProvinceName);
           }else {
               list=institutionDataService.selectListByCity(name);
           }
           for(InstitutionDateDto ins:list){
               resultMap.add(processInstitutionData(ins));
           }
           TableDataInfo rspData = new TableDataInfo();
           rspData.setCode(HttpStatus.SUCCESS);
           rspData.setMsg("查询成功");
           rspData.setRows(resultMap);
           rspData.setTotal(new PageInfo(resultMap).getTotal());
           return rspData;
       }catch (Exception e){
           logger.error("查询机构列表失败:{}",cityName,e);
       }
       return getDataTable(Collections.emptyList());
    }
    @GetMapping("/listbydistance")
    private TableDataInfo ListByDistance(@RequestParam (required = false) Double latitude,
                                         @RequestParam (required = false) Double longitude,
                                         @RequestParam (required = false) String city,
                                         @RequestParam(name = "pageNum", defaultValue = "1")Integer pageNum,
                                         @RequestParam(name = "pageSize", defaultValue = "3")Integer pageSize){
        startPage();
        if (latitude == null) {
            // 默认使用温州洞头区的坐标

            latitude = 27.836088;
        }
        if (longitude == null) {
            longitude = 121.157463;
        }
        List<Map<String,Object>> resultMap=new ArrayList<>();
        List<InstitutionDateDto> list=institutionDataService.selectListByDistance(latitude,longitude,city);
        for(InstitutionDateDto ins:list){
            resultMap.add(processInstitutionData(ins));
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(resultMap);
        rspData.setTotal(new PageInfo(resultMap).getTotal());
        return rspData;
//        return getDataTable(list);
    }


    @GetMapping("/picture")
    public Result<List<SwiperPicture>> getPicture(@RequestParam Integer page){
        return Result.success(iSwiperPictureService.selectSwiperPic(page));
    }
//
    @GetMapping("/getprovince")
    public Result<List<Province>> getProvince(){
        List<Province> provinceList=provinceService.getProvinceList();
        int man=100;
        for(Province list:provinceList){
            list.setValue(list.getId());
            List<CityDto> citys=new ArrayList<>();
            citys=cityService.selectList(list.getId());
            int rank=1;
            for (CityDto cityDto:citys){
                cityDto.setId(man+rank);
                cityDto.setValue(rank++);
            }
            list.setChildren(citys);
            man+=100;
        }
        return Result.success(provinceList);
    }
    @PostMapping("/qualification")
    public AjaxResult submitQualification(@RequestBody QualificationDto qualificationDto){
        try {
            qualificationRecordService.insert(qualificationDto.getPhone(),qualificationDto.getFilePath());
            return AjaxResult.success("保存成功");
        } catch (Exception e) {
            logger.error("上传记录保存失败", e);
            return AjaxResult.error(e.getMessage());
        }
    }
    @PostMapping("/test-base64")
    public AjaxResult testBase64(@RequestBody QualificationDto dto) {
        String base64 = dto.getFilePath();

        Map<String, Object> result = new HashMap<>();
        result.put("totalLength", base64.length());
        result.put("startsWithPrefix", base64.startsWith("data:image"));
        result.put("containsSeparator", base64.contains(";base64,"));

        if (base64.contains(";base64,")) {
            String[] parts = base64.split(";base64,");
            result.put("parts", parts.length);
            if (parts.length == 2) {
                result.put("fileType", parts[0]);
                result.put("dataLength", parts[1].length());
            }
        }

        return AjaxResult.success(result);
    }
    @GetMapping("/getinslistbyid")
    public Result<Map<String,Object>> getListById(@RequestParam Integer insId){
        Map<String,Object> resultMap=new LinkedHashMap<>();
        InstitutionData ins=institutionDataService.selectListById(insId);
        if(ins==null){
            return Result.error("当前机构不存在");
        }
        ins.setCompanyName(processCompanyName(ins.getCompanyName()));
        resultMap.put("id",ins.getId());
        resultMap.put("insName",ins.getCompanyName());
        resultMap.put("insTags",ins.getTags());
        SimplePropertyPreFilter filter=new SimplePropertyPreFilter();
        filter.getExcludes().addAll(Arrays.asList("id", "courses", "courseTags", "insTag","tags"));
        String jsonString=JSON.toJSONString(ins,filter,SerializerFeature.WriteMapNullValue);
        Map<String,Object> map=JSON.parseObject(jsonString, LinkedHashMap.class);
        resultMap.put("institution",map);
        ins.setCourses(courseService.selectCourseList(ins.getId()));
        List<Course> courseList=ins.getCourses();
        if(courseList!=null && !courseList.isEmpty()){
            Course course=courseList.get(0);
            if(ins.getCourseTags()==null){
                ins.setCourseTags(new ArrayList<>());
            }
            // 将构建的字符串添加到tags列表中
            ins.getCourseTags().add(course.getExpense()+"元起" );
            ins.getCourseTags().add(course.getName());
            ins.getCourseTags().add(course.getSubject());
        }
        resultMap.put("courses",courseList);
        resultMap.put("courseTag",ins.getCourseTags());
        ins.setCourses(courseService.selectCourseListByInstitution(ins.getId()));
        return Result.success(resultMap);
    }
    @GetMapping("/getcomments")
    public Result<Map<String,Object>> getUserComments(@RequestParam Integer insId,
                                                    @RequestParam Integer customerId,
                                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                                    @RequestParam(defaultValue = "10") Integer pageSize){
        try {
            if(iInstitutionService.selectInstitutionById(insId)==null){
                return Result.error("当前机构不存在");
            }
            List<CommentDto> list=commentService.selectCommentList(insId,customerId);
            Map<String,Object> resultMap=new LinkedHashMap<>();
            double averageCoachScore=list.stream().mapToDouble(com->com.getCoachScore()!=null?com.getCoachScore():0).average().orElse(0)/5 * 100;
            double averageServiceScore=list.stream().mapToDouble(com->com.getServiceScore()!=null?com.getServiceScore():0).average().orElse(0)/5 * 100;
            double averageFeeScore=list.stream().mapToDouble(com->com.getFeeScore()!=null?com.getServiceScore():0).average().orElse(0)/5 * 100;
            double score=list.stream().mapToDouble(com->com.getFeeScore()!=null?com.getAverageScore():0).average().orElse(0);
            BigDecimal bd = new BigDecimal(score);
            bd = bd.setScale(1, RoundingMode.HALF_UP);
            double roundedScore = bd.doubleValue();
            Institution institution=iInstitutionService.selectInstitutionById(insId);
            institution.setScore(roundedScore);
            iInstitutionService.updateInstitution(institution);
            String formattedAverageCoachScore = String.format("%.0f%%", averageCoachScore);
            String formattedAverageServiceScore = String.format("%.0f%%", averageServiceScore);
            String formattedAverageFeeScore = String.format("%.0f%%", averageFeeScore);
            resultMap.put("average",roundedScore);
            resultMap.put("averageCoachScore",formattedAverageCoachScore);
            resultMap.put("averageServiceScore",formattedAverageServiceScore);
            resultMap.put("averageFeeScore",formattedAverageFeeScore);
            resultMap.put("data",list);
            return Result.success(resultMap);
        }catch (Exception e){
            logger.error("获取评论列表失败");
            return Result.error("获取评论列表失败:"+e.getMessage());
        }

    }
    @PostMapping("/addcomment")
    public Result<Map<String,Object>>addComments(@RequestBody CommentDto commentDto){
        try {
            if(commentDto.getComment()==null||commentDto==null){
                return Result.error("评论数据不能为空");
            }
            String scoreValidation=validateScores(commentDto);
            if(scoreValidation!=null){
                return Result.error(scoreValidation);
            }
            double avg=(commentDto.getFeeScore()+commentDto.getServiceScore()+commentDto.getCoachScore())/3.0;
            if (avg % 1 >= 0.45) {
                // 如果大于等于0.45，则向上取整到下一个整数，并保留一位小数
                avg = Math.ceil(avg * 10) / 10.0;
            } else {
                // 否则，直接四舍五入到一位小数
                avg = Math.round(avg * 10) / 10.0;
            }
            commentDto.setAverageScore(avg);
            commentDto.setCreateTime(new Date());
            int result=commentService.addcomment(commentDto);
            if (result<=0){
                return Result.error("添加评论失败");
            }
            Map<String,Object> resultmap=new HashMap<>();
            resultmap.put("message","评论成功");
            resultmap.put("commentId",commentDto.getId());
            resultmap.put("commentDetail",getCommentDetail(commentDto));
            return Result.success("评论成功",resultmap);
        }catch (Exception e){
            return Result.error("评论失败："+e.getMessage());
        }
    }
    @DeleteMapping("/delcomment/{commentId}")
    public AjaxResult deleteComments(@PathVariable Integer commentId){
        return AjaxResult.success(commentService.deleteById(commentId));
    }

    @GetMapping("/prise")
    public AjaxResult commentPraise(@RequestParam Integer customerId,
                                @RequestParam Integer commentId){
        try{
            boolean isLiked=commentService.praise(commentId,customerId);
            return AjaxResult.success(isLiked?"点赞成功":"取消点赞成功");
        }catch (Exception e){
            return AjaxResult.error("点赞失败"+e.getMessage());
        }
    }
//    @PostMapping("/register/step1")
//    public AjaxResult register(@RequestBody CustomerRegisterDto customerRegisterDto){
//
//        try {
//            institutionDataService.insertCustomer(customerRegisterDto);
//            return AjaxResult.success("注册成功");
//        }catch (Exception e){
//            return AjaxResult.error(e.getMessage());
//        }
//    }

    private Map<String,Object> getCommentDetail(CommentDto commentDto){
        Map<String,Object> map=new HashMap<>();
        Field[] fields=CommentDto.class.getDeclaredFields();
        for(Field f:fields){
            try {
                f.setAccessible(true);
                if(!"id".equals(f.getName())){
                    map.put(f.getName(),f.get(commentDto));
                }
            }catch (IllegalAccessException e){
                logger.error("获取字段值失败:"+f.getName(),e);
            }
        }
        return map;
    }
    private boolean isValidScore(Integer score) {
        return score != null && score >= 1 && score <= 5;
    }
    private String validateScores(CommentDto commentDto){
        if(!isValidScore(commentDto.getFeeScore())){
            return "收费评分必须在1-5之间";
        }
        if(!isValidScore(commentDto.getServiceScore())){
            return "服务评分必须在1-5之间";
        }
        if(!isValidScore(commentDto.getCoachScore())){
            return "教练评分必须在1-5之间";
        }
        return null;
    }

    private boolean inProvinceLevel(String name){
        Set<String> provinceShortNames=new HashSet<>(Arrays.asList(
                "北京", "上海", "天津", "重庆",
                "新疆", "广西", "内蒙古", "宁夏", "西藏",
                "浙江", "云南", "四川", "贵州", "河南", "河北", "山东", "山西",
                "江苏", "江西", "湖南", "湖北", "广东", "海南", "福建", "台湾",
                "甘肃", "青海", "安徽", "吉林", "辽宁", "黑龙江"
        ));
        return name.endsWith("省") ||
                name.endsWith("自治区") ||
                name.endsWith("市")||
                provinceShortNames.contains(name);

    }
    private String processCompanyName(String companyName) {
        if (StringUtils.isNotEmpty(companyName)) {
            return companyName.replaceAll("科技有限公司|有限公司", "");
        }
        return companyName;
    }
    private String getFullProvinceName(String shortName){
        Map<String,String> provinceMap=new HashMap<>();
        provinceMap.put("新疆", "新疆维吾尔自治区");
        provinceMap.put("广西", "广西壮族自治区");
        provinceMap.put("内蒙古", "内蒙古自治区");
        provinceMap.put("宁夏", "宁夏回族自治区");
        provinceMap.put("西藏", "西藏自治区");
        // 直辖市
        provinceMap.put("北京", "北京市");
        provinceMap.put("上海", "上海市");
        provinceMap.put("天津", "天津市");
        provinceMap.put("重庆", "重庆市");
        if (provinceMap.containsKey(shortName)) {
            return provinceMap.get(shortName);
        }
        if (shortName.endsWith("省")) {
            return shortName;
        } else {
            return shortName + "省";
        }
    }
    private Map<String,Object> processInstitutionData(InstitutionDateDto ins){
        Map<String,Object> resultMap=new LinkedHashMap<>();
        ins.setCompanyName(processCompanyName(ins.getCompanyName()));
        resultMap.put("id",ins.getId());
        resultMap.put("insName",ins.getCompanyName());
        resultMap.put("insTags",ins.getTags());
        SimplePropertyPreFilter filter=new SimplePropertyPreFilter();
        filter.getExcludes().addAll(Arrays.asList("id", "courses", "courseTags", "insTag","tags"));
        String jsonString=JSON.toJSONString(ins,filter,SerializerFeature.WriteMapNullValue);
        Map<String,Object> map=JSON.parseObject(jsonString, LinkedHashMap.class);
        resultMap.put("institution",map);
        ins.setCourses(courseService.selectCourseList(ins.getId()));
        List<Course> courseList=ins.getCourses();
        if(courseList!=null && !courseList.isEmpty()){
            Course course=courseList.get(0);
            if(ins.getCourseTags()==null){
                ins.setCourseTags(new ArrayList<>());
            }
            // 将构建的字符串添加到tags列表中
            ins.getCourseTags().add(course.getExpense()+"元起" );
            ins.getCourseTags().add(course.getName());
            ins.getCourseTags().add(course.getSubject());
        }
        resultMap.put("courses",courseList);
        resultMap.put("courseTag",ins.getCourseTags());
        ins.setCourses(courseService.selectCourseListByInstitution(ins.getId()));
        return resultMap;
    }
    private Map<String,Object> createInstitution(InstitutionData ins){
        Map<String, Object> result = new LinkedHashMap<>();

        try {
            Field[] fields=InstitutionData.class.getDeclaredFields();
            for(Field f:fields){
                f.setAccessible(true);
                if("id".equals(f.getName())||"courses".equals(f.getName())||"courseTags".equals(f.getName())
                ||"insTag".equals(f.getName())){
                    continue;
                }
                Object value=f.get(ins);
                if(value!=null);
                result.put(f.getName(),value);
            }
        }catch (Exception e){
            logger.error("处理对象信息失败");

        }
        return result;
    }

}
