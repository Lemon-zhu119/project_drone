package com.ruoyi.api.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.api.common.Result;
import com.ruoyi.api.domain.dto.SmsRecord;
import com.ruoyi.api.service.Impl.SmsServiceImpl;
import com.ruoyi.api.service.SmsRecordService;
import com.ruoyi.api.service.SmsService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.web.controller.customer.domain.Customer;
import com.ruoyi.web.controller.customer.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Lemon-zhu119
 * @date 2025/03/09
 */
@RestController
@RequestMapping("api/code")
public class CodeController {
    /**  */
    @Autowired
    private ICustomerService customerService;
    /**  */
    @Autowired
    private SmsService smsService;
    /**  */
    @Autowired
    private SmsRecordService smsRecordService;
    /**  */
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**  */
    private static final Logger logger= LoggerFactory.getLogger(CodeController.class);

    /**
     * @param phone
     * @return {@link Result }<{@link Map }<{@link String },{@link String }>>
     */
    @GetMapping("/sendcode")
    public Result<Map<String,String>> sendVerificationCode(@RequestParam String phone){
        try {
            if(!isValidPhoneNumber(phone)){
                return Result.error("无效的手机号");
            }
            String code=generateVerificationCode();
            String countKey = "sms:count:" + phone;
            Long sendCount = redisTemplate.opsForValue().increment(countKey, 1);
            if (sendCount == 1) {
                // 如果是第一次发送，设置过期时间为一天
                redisTemplate.expire(countKey, 1, TimeUnit.DAYS);
            }
            if (sendCount > 10) {
                return Result.error("验证码发送次数超当日次数限制");
            }
            boolean sent=smsService.sendVerificationCode(phone,code);
            if(redisTemplate.opsForValue().get("sms:lastSend"+phone)!=null){
                return Result.error("请稍后再试");
            }
            if(!sent){
                return Result.error("验证码发送失败");
            }
            redisTemplate.opsForValue().set("sms:code"+phone,code,5,TimeUnit.MINUTES);
            redisTemplate.opsForValue().set("sms:lastSend"+phone,"1",1,TimeUnit.MINUTES);
            Map<String,String> resultMap=new HashMap<>();
            resultMap.put("message","验证码已经发送");
            resultMap.put("code",code);
            return Result.success(resultMap);
        }catch (Exception e){
            return Result.error("发送验证码失败"+e.getMessage());
        }

    }

    /**
     * @param requestBody
     * @return {@link String }
     */
    @PostMapping("/callback")
    private String receiveStatus(@RequestBody String requestBody){
        try{
            JSONObject json= JSON.parseObject(requestBody);
            String phone=json.getString("phone_number");
            String sendStatus=json.getString("send_status");
            String errCode=json.getString("err_code");
            String outId=json.getString("out_id");
            SmsRecord record=new SmsRecord();
            record.setPhone(phone);
            record.setStatus("SUCCESS".equals(sendStatus)?2:3);
            record.setErrorMsg(errCode);
            smsRecordService.updateStatus(record);
            return "success";
        }catch (Exception e){
            logger.error("处理短信状态推送失败");
            return "fail";
        }
    }

    /**
     * @param phone
     * @param code
     * @return {@link Result }<{@link Customer }>
     */
    @GetMapping("/loginbycode")
    public AjaxResult loginByCode(@RequestParam String phone,@RequestParam String code){
        try {
            if(!isValidPhoneNumber(phone)){
                return AjaxResult.error("无效的手机号");
            }
            String savedCode=redisTemplate.opsForValue().get("sms:code"+phone);
            if(savedCode==null){
                return AjaxResult.error("验证码已过期");
            }
            if(!savedCode.equals(code)){
                return AjaxResult.error("验证码错误");
            }
            redisTemplate.delete("sms:code"+phone);
            Customer customer=customerService.findCustomerByPhone(phone);
            if(customer==null){
                customer=new Customer();
                customer.setPhone(phone);
                customer.setUsername("user_"+phone.substring(phone.length()-4));
                logger.info("新用户注册-手机号:{}",phone);
                int rows=customerService.insertCustomer(customer);
                if(rows<=0){
                    return AjaxResult.error("用户注册失败");
                }
                customer=customerService.findCustomerByPhone(phone);
                if(customer==null){
                    return AjaxResult.error("用户信息获取失败");
                }
            }
            logger.info("用户登录成功-手机号:{}",phone);
            String token = customerService.loginSuccess(customer);
            AjaxResult ajax = AjaxResult.success();
            ajax.put(Constants.TOKEN, token);
            Customer customerDB= customerService.findCustomerByPhone(customer.getPhone());
            customerDB.setPassword(null);
            ajax.put("user", customerDB);
            return ajax;
        }catch (Exception e){
            return AjaxResult.error("登录失败："+e.getMessage());
        }
    }

    /**
     * @param customer
     * @param customerService
     * @return 操作消息提醒
     * @throws NoSuchAlgorithmException
     */
    static AjaxResult getAjaxResult(Customer customer, ICustomerService customerService) throws NoSuchAlgorithmException {
        String token = customerService.login(customer);
        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.TOKEN, token);
        Customer customerDB= customerService.findCustomerByPhone(customer.getPhone());
        customerDB.setPassword(null);
        ajax.put("user", customerDB);
        return ajax;
    }


    /**
     * @param phone
     * @return boolean
     */
    private boolean isValidPhoneNumber(String phone){
        return phone !=null && phone.matches("^1[3-9]\\d{9}$");
    }

    /**
     * @return {@link String }
     */
    private String generateVerificationCode(){
        return String.format("%06d", (int) (Math.random() * 1000000));
    }
}
