package com.ruoyi.web.controller.institutionmore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ruoyi.api.domain.dto.QualificationDto;
import com.ruoyi.api.util.FileUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.customer.domain.Customer;
import com.ruoyi.web.controller.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.institutionmore.mapper.InstitutionMapper;
import com.ruoyi.web.controller.institutionmore.domain.Institution;
import com.ruoyi.web.controller.institutionmore.service.IInstitutionService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 机构信息详情管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-16
 */
@Service
public class InstitutionServiceImpl implements IInstitutionService
{
    @Autowired
    private InstitutionMapper institutionMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private FileUtil fileUtil;

    /**
     * 查询机构信息详情管理
     *
     * @param id 机构信息详情管理主键
     * @return 机构信息详情管理
     */
    @Override
    public Institution selectInstitutionById(Integer id)
    {
        return institutionMapper.selectInstitutionById(id);
    }

    /**
     * 查询机构信息详情管理列表
     *
     * @param institution 机构信息详情管理
     * @return 机构信息详情管理
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Institution> selectInstitutionList(Institution institution)
    {
        List<Institution> list=institutionMapper.selectInstitutionList(institution);
        for(Institution ins:list){
            Double avgScore=institutionMapper.getAverageScore(ins.getId());
            ins.setScore(avgScore);
            institutionMapper.updateInstitution(ins);
        }
        return list;
    }

    /**
     * 新增机构信息详情管理
     *
     * @param institution 机构信息详情管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertInstitution(Institution institution)
    {
        institution.setCreateTime(DateUtils.getNowDate());
        return institutionMapper.insertInstitution(institution);
    }

    /**
     * 修改机构信息详情管理
     *
     * @param institution 机构信息详情管理
     * @return 结果
     */
    @Override
    public int updateInstitution(Institution institution)
    {
        institution.setUpdateTime(DateUtils.getNowDate());
        return institutionMapper.updateInstitution(institution);
    }

    /**
     * 批量删除机构信息详情管理
     *
     * @param ids 需要删除的机构信息详情管理主键
     * @return 结果
     */
    @Override
    public int deleteInstitutionByIds(Integer[] ids)
    {
        return institutionMapper.deleteInstitutionByIds(ids);
    }

    /**
     * 删除机构信息详情管理信息
     *
     * @param id 机构信息详情管理主键
     * @return 结果
     */
    @Override
    public int deleteInstitutionById(Integer id)
    {
        return institutionMapper.deleteInstitutionById(id);
    }

    @Override
    public int getMaxOrderId() {
        return institutionMapper.getMaxOrderId();
    }

    @Override
    public Double getAverageScore(Integer institutionId) {
        return institutionMapper.getAverageScore(institutionId);
    }

    @Override
    public List<Institution> updateWithRank() {
        List<Institution> list=institutionMapper.selectByDesc();
        int rank=1;
        for(Institution ins:list){
            ins.setOrderId(rank++);
        }
        for(Institution ins:list){
            institutionMapper.updateOrderId(ins.getId(),ins.getOrderId());
        }
       return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void qualification(String phone, List<MultipartFile> files) {
        Customer customer=new Customer();
        customer.setPhone(phone);
        List<Customer> customers=customerMapper.selectCustomerList(customer);
        if(customer==null||customers.isEmpty()){
            throw new ServiceException("用户信息不存在");
        }
        if(customers.get(0).getId()==null){
            throw new ServiceException("用户id不能为空");
        }
        List<Customer> customer1=customerMapper.selectCustomerList(customer);
        Institution institution=institutionMapper.selectInstitutionByUserId(customer1.get(0).getId().intValue());
        if(institution==null){
            throw new ServiceException("未找到对应机构信息");
        }
//   机构和用户绑定,暂时不写     institution.setUserId(customer1.get(0).getId().intValue());
        Map<String, Object> map=new HashMap<>();
        map.put("userId",customer1.get(0).getId().intValue());
        map.put("id",institution.getId());
        if(institution==null){
            throw new ServiceException("未找到对应机构信息");
        }
        if(files!=null&&!files.isEmpty()){
            try {
                fileUtil.uploadFiles(files);
            }catch (Exception e){
                throw new ServiceException("文件上传失败:"+e.getMessage());
            }

        }
        institutionMapper.update(map);

    }

    @Override
    public int editUrl(String url, Integer id) {
        return institutionMapper.editUrl(url,id);
    }


    @Override
    public List<String> selectProvinceList() {
        return institutionMapper.selectProvinceList();
    }


}
