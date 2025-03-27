package com.ruoyi.web.controller.customer.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.ruoyi.web.controller.customer.domain.Customer;

/**
 * 用户信息管理Service接口
 *
 * @author ruoyi
 * @date 2024-12-11
 */
public interface ICustomerService
{
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
     * 批量删除用户信息管理
     *
     * @param ids 需要删除的用户信息管理主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    /**
     * 删除用户信息管理信息
     *
     * @param id 用户信息管理主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    String login(Customer customer) throws NoSuchAlgorithmException;

    boolean logout(String phone);

    boolean insert(Customer customer);

    Long getLastId();

    Customer findCustomerByUsername(String username);

    Customer findCustomerByPhone(String phone);

    Customer findById(Long id);


    Long getAnswerNumber(Long customerId);

    boolean deleteCustomerByIdAndPassword(Long id, String password);


    String loginSuccess(Customer customer);
}
