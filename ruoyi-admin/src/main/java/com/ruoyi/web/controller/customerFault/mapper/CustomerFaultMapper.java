package com.ruoyi.web.controller.customerFault.mapper;

import java.util.List;

import com.ruoyi.api.domain.dto.CustomerFaultDto;
import com.ruoyi.web.controller.customerFault.domain.CustomerFault;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户错题表Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-16
 */
@Mapper
public interface CustomerFaultMapper
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
     * 删除用户错题表
     *
     * @param id 用户错题表主键
     * @return 结果
     */
    public int deleteCustomerFaultById(Integer id);

    /**
     * 批量删除用户错题表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerFaultByIds(Integer[] ids);

    List<CustomerFaultDto> selectCustomerFaultDtoList(@Param("customerId") Long customerId);
}
