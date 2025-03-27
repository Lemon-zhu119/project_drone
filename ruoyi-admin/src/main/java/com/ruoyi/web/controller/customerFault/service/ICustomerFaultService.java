package com.ruoyi.web.controller.customerFault.service;

import java.util.List;

import com.ruoyi.api.domain.dto.CustomerFaultDto;
import com.ruoyi.web.controller.customerFault.domain.CustomerFault;

/**
 * 用户错题表Service接口
 * 
 * @author ruoyi
 * @date 2024-12-16
 */
public interface ICustomerFaultService 
{
    /**
     * 查询用户错题表
     * 
     * @param id 用户错题表主键
     * @return 用户错题表
     */
    public CustomerFault selectCustomerFaultById(Integer id);

    /**
     * 查询用户错题表列表
     * 
     * @param customerFault 用户错题表
     * @return 用户错题表集合
     */
    public List<CustomerFault> selectCustomerFaultList(CustomerFault customerFault);

    /**
     * 新增用户错题表
     * 
     * @param customerFault 用户错题表
     * @return 结果
     */
    public int insertCustomerFault(CustomerFault customerFault);

    /**
     * 修改用户错题表
     * 
     * @param customerFault 用户错题表
     * @return 结果
     */
    public int updateCustomerFault(CustomerFault customerFault);

    /**
     * 批量删除用户错题表
     * 
     * @param ids 需要删除的用户错题表主键集合
     * @return 结果
     */
    public int deleteCustomerFaultByIds(Integer[] ids);

    /**
     * 删除用户错题表信息
     * 
     * @param id 用户错题表主键
     * @return 结果
     */
    public int deleteCustomerFaultById(Integer id);

    List<CustomerFaultDto> selectCustomerFaultDtoList(Long customerId);
}
