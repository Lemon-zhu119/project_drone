package com.ruoyi.api.service;

import com.ruoyi.api.domain.Province;

import java.util.List;

public interface SmsService {


    boolean sendVerificationCode(String phone,String code);

}
