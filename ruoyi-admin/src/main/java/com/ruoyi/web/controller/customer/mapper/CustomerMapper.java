package com.ruoyi.web.controller.customer.mapper;

import java.util.List;
import com.ruoyi.web.controller.customer.domain.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
/**
 * 用户信息管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-11
 */
public interface CustomerMapper
{

    public Customer findUserByUsername(String username);

    /**
     * 查询用户信息管理
     *
     * @param id 用户信息管理主键
     * @return 用户信息管理
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询用户信息管理列表
     *
     * @param customer 用户信息管理
     * @return 用户信息管理集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增用户信息管理
     *
     * @param customer 用户信息管理
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改用户信息管理
     *
     * @param customer 用户信息管理
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 删除用户信息管理
     *
     * @param id 用户信息管理主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 批量删除用户信息管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    void updateOnline(@Param("i") int i, @Param("phone") String phone);

    boolean insert(Customer customer);

    Long getLastId();

    Customer findCustomerByPhone(String phone);

    Customer findUserById(@Param("id") Long id);

    boolean checkUserNameExist(String username);

    boolean checkPhoneExist(String phone);

    Long selectAnswerNumberByCustomerId(@Param("customerId") Long customerId);
}
