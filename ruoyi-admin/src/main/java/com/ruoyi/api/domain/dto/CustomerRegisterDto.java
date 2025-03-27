package com.ruoyi.api.domain.dto;

import com.ruoyi.web.controller.customer.domain.Customer;

public class CustomerRegisterDto extends Customer {
    public Integer RegisterStep;
    public String UserType;

    public Integer getRegisterStep() {
        return RegisterStep;
    }

    public void setRegisterStep(Integer registerStep) {
        RegisterStep = registerStep;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public CustomerRegisterDto(Integer registerStep, String userType) {
        RegisterStep = registerStep;
        UserType = userType;
    }




}
