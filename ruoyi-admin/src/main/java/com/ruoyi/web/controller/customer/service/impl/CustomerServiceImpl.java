package com.ruoyi.web.controller.customer.service.impl;

import com.ruoyi.api.common.PasswordUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.web.exception.GlobalExceptionHandler;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.web.controller.customer.domain.Customer;
import com.ruoyi.web.controller.customer.mapper.CustomerMapper;
import com.ruoyi.web.controller.customer.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 用户信息管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-11
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private CustomerMapper customerMapper;
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    /**
     * 查询用户信息管理
     *
     * @param id 用户信息管理主键
     * @return 用户信息管理
     */
    @Override
    public Customer selectCustomerById(Long id) {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询用户信息管理列表
     *
     * @param customer 用户信息管理
     * @return 用户信息管理
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer) {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增用户信息管理
     *
     * @param customer 用户信息管理
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer) {

        customer.setCreateTime(DateUtils.getNowDate());
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改用户信息管理
     *
     * @param customer 用户信息管理
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer) {
        customer.setUpdateTime(DateUtils.getNowDate());
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除用户信息管理
     *
     * @param ids 需要删除的用户信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids) {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除用户信息管理信息
     *
     * @param id 用户信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Long id) {
        return customerMapper.deleteCustomerById(id);
    }

    @Override
    public String login(Customer customer) throws NoSuchAlgorithmException {
        MessageDigest md5 = null;
        // 生成普通的MD5密码
        md5 = MessageDigest.getInstance("MD5");
        char[] charArray = customer.getPassword().toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        customer.setPassword(hexValue.toString());
        Customer getCustomer = customerMapper.findCustomerByPhone(customer.getPhone());
        if (getCustomer == null) {
            //用户不存在！
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(customer.getPhone(), Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new ServiceException(MessageUtils.message("user.password.not.match"));
        }
        if (!getCustomer.getPassword().equals(customer.getPassword())) {
            //用户名或者密码错误！
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(customer.getPhone(), Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new ServiceException(MessageUtils.message("user.password.not.match"));
        }
        return loginSuccess(getCustomer);
    }

    @Override
    public String loginSuccess(Customer customer) {
        // 设定登录成功消息
        customerMapper.updateOnline(1, customer.getPhone());
        LoginUser loginUser = new LoginUser();
        SysUser user = new SysUser(customer.getId());
        user.setUserName(customer.getUsername());
        user.setPassword(customer.getPassword());
        loginUser.setUser(user);
        // 生成token
        return tokenService.createToken(loginUser);
    }

    @Override
    public boolean logout(String phone) {
        customerMapper.updateOnline(0, phone);
        return true;
    }

    @Override
    public boolean insert(Customer customer) {
        return customerMapper.insert(customer);
    }

    @Override
    public Long getLastId() {
        return customerMapper.getLastId();
    }

    @Override
    public Customer findCustomerByUsername(String username) {
        return customerMapper.findUserByUsername(username);
    }

    @Override
    public Customer findCustomerByPhone(String phone) {
        return customerMapper.findCustomerByPhone(phone);
    }

    @Override
    public Customer findById(Long id) {
        return customerMapper.findUserById(id);
    }

    @Override
    public Long getAnswerNumber(Long customerId) {
        return customerMapper.selectAnswerNumberByCustomerId(customerId);
    }

    @Override
    public boolean deleteCustomerByIdAndPassword(Long id, String password) {
        Customer customer = customerMapper.findUserById(id);
        password = PasswordUtil.md5Encrypt(password);
        if (password.equals(customer.getPassword())) {
            return customerMapper.deleteCustomerById(id) == 1;
        } else {
            return false;
        }
    }
}
