package com.ruoyi.api.controller;

import com.ruoyi.api.common.PasswordUtil;
import com.ruoyi.api.common.Result;
import com.ruoyi.api.domain.dto.PasswordUpdateDTO;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.web.controller.customer.domain.Customer;
import com.ruoyi.web.controller.customer.service.ICustomerService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

import static com.ruoyi.api.controller.CodeController.getAjaxResult;

/**
 * @author Lemon-zhu119
 * @date 2025-03-11
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    /**  */
    @Autowired
    private ICustomerService customerService;

    /**
     *
     * @param customer
     * @return 操作消息提醒
     * @throws NoSuchAlgorithmException
     */
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestBody Customer customer) throws NoSuchAlgorithmException {
        return getAjaxResult(customer, customerService);
    }

    /**
     * @param phone
     * @return
     */
    @CrossOrigin
    @GetMapping("/logout")
    public Result<String> logout(@RequestParam String phone) {
        boolean logout = customerService.logout(phone);
        if (logout) {
            return Result.success("登出成功");
        } else {
            return Result.error("登出失败");
        }
    }

    /**
     * @param id
     * @param password
     * @return
     */
    @CrossOrigin
    @PostMapping("/delete")
    public Result<String> delete(@RequestParam Integer id,@RequestParam String password) {
        boolean delete = customerService.deleteCustomerByIdAndPassword(Long.valueOf(id),password);
        if (delete) {
            return Result.success("注销成功");
        } else {
            return Result.error("注销失败");
        }
    }

    /**
     * @param customer
     * @return
     * @throws NoSuchAlgorithmException
     */
    @CrossOrigin
    @PostMapping("/register")
    @ResponseBody
    public Result<String> insert(@RequestBody Customer customer) throws NoSuchAlgorithmException {
        customer.setId(customerService.getLastId() + 1);
        customer.setPassword(PasswordUtil.md5Encrypt(customer.getPassword()));
        boolean save = customerService.insert(customer);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }

    /**
     * @param customer
     * @return
     */
    @PostMapping("/updateBasic")
    @ResponseBody
    public Result<String> updateBasicInfo(@RequestBody Customer customer) {
        Customer customerDB = customerService.findById(customer.getId());
        if (customerDB != null) {
            // 只更新基本信息，不涉及手机号和密码
            customer.setId(customerDB.getId());
            // 其他基本信息字段...
            int save = customerService.updateCustomer(customer);
            if (save == 1) {
                return Result.success("成功更新用户信息","基本信息更新成功");
            }
            return Result.error("更新失败");
        }
        return Result.error("用户不存在");
    }

    /**
     * @param id
     * @return
     * @throws NoSuchAlgorithmException
     */
    @GetMapping("/getPassword")
    public Result<String> GetPassword(@RequestParam Long id) throws NoSuchAlgorithmException {
        Customer customerDB = customerService.findById(id);
        if (customerDB != null) {
            if(customerDB.getPassword()==null||customerDB.getPassword().equals("")){
                return Result.success("无密码");
            }else {
                return Result.success("有密码");
            }
        }
        return Result.error("用户不存在");
    }

    /**
     * @param dto
     * @return
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public Result<String> updatePassword(@RequestBody PasswordUpdateDTO dto) throws NoSuchAlgorithmException {
        Customer customerDB = customerService.findById(dto.getId());
        if (customerDB != null) {
            // 检查用户是否有原密码
            if (customerDB.getPassword() == null || customerDB.getPassword().isEmpty()) {
                // 如果没有原密码，直接更新新密码
                customerDB.setPassword(PasswordUtil.md5Encrypt(dto.getNewPassword()));
                int save = customerService.updateCustomer(customerDB);
                if (save == 1) {
                    return Result.success("密码修改成功");
                }
                return Result.error("密码修改失败");
            } else {
                // 验证原密码
                if (!customerDB.getPassword().equals(PasswordUtil.md5Encrypt(dto.getOldPassword()))) {
                    return Result.error("原密码错误");
                }

                // 检查新密码是否与旧密码一致
                if (customerDB.getPassword().equals(PasswordUtil.md5Encrypt(dto.getNewPassword()))) {
                    return Result.error("新密码不能与旧密码一致");
                }

                // 更新新密码
                customerDB.setPassword(PasswordUtil.md5Encrypt(dto.getNewPassword()));
                int save = customerService.updateCustomer(customerDB);
                if (save == 1) {
                    return Result.success("密码修改成功");
                }
                return Result.error("密码修改失败");
            }
        }
        return Result.error("用户不存在");
    }

    /**
     * @param request
     * @return 操作消息提醒
     */
    @GetMapping("/info")
    @ResponseBody
    public AjaxResult getUserInfo(HttpServletRequest request) {
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        SysUser user=  loginUser.getUser();
        Customer customer = customerService.findById(user.getUserId());
        if (customer != null) {
            // 清除敏感信息
            customer.setPassword(null);
            return AjaxResult.success(customer);
        }
        return AjaxResult.error("用户不存在");
    }
}
