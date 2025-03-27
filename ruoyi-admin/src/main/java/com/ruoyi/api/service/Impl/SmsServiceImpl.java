package com.ruoyi.api.service.Impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.ruoyi.api.config.SmsConfig;
import com.ruoyi.api.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Service
public class SmsServiceImpl implements SmsService {


    @Value("${aliyun.sms.accessKeyId}")

    private String accessKeyId;



    @Value("${aliyun.sms.accessKeySecret}")

    private String accessKeySecret;



    @Value("${aliyun.sms.signName}")

    private String signName;



    @Value("${aliyun.sms.templateCode}")

    private String templateCode;



    @Value("${aliyun.sms.endpoint}")

    private String endpoint;
    private static final Logger logger= LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private SmsConfig smsConfig;
    @Override
    public boolean sendVerificationCode(String phone, String code) {

        try {
            DefaultProfile profile=DefaultProfile.getProfile("cn-hangzhou",
                    accessKeyId,
                    accessKeySecret);
            IAcsClient client=new DefaultAcsClient(profile);
            SendSmsRequest request=new SendSmsRequest();
            request.setPhoneNumbers(phone);
            request.setSignName(signName);
            request.setTemplateCode(templateCode);

            Map<String,String> templateParam=new HashMap<>();
            templateParam.put("code",code);
            request.setTemplateParam(JSON.toJSONString(templateParam));

            SendSmsResponse response=client.getAcsResponse(request);
            if (response.getCode()!=null&&response.getCode().equals("OK")){
                logger.info("短信发送成功-手机号:{},验证码:{}",phone,code);
                return true;
            }else {
                logger.error("短信发送失败-手机号:{},验证码:{},错误信息:{}",phone,response.getCode(),response.getMessage());
                return false;
            }
        }catch (ServerException e){
            logger.error("短信服务端异常 - 手机号:{},错误码:{}，错误信息:{}",phone,e.getErrCode(),e.getErrMsg());
            return false;
        }catch (ClientException e){
            logger.error("短信客户端异常-手机号:{},错误码:{},错误信息:{}",phone,e.getErrCode(),e.getErrMsg());
            return false;
        }catch (Exception e){
            logger.error("短信发送未知异常-手机号:{},错误码{}，错误信息,",phone,e.getClass().getName(),e.getMessage());
            return false;
        }
    }




}
