package com.ruoyi.api.domain.dto;

import com.ruoyi.web.controller.customerFault.domain.CustomerFault;

import java.util.Date;

public class CustomerFaultDto extends CustomerFault {
    private String title;
    private Double errorRate;

    @Override
    public String toString() {
        return "CustomerFaultDto{" +
                "title='" + title + '\'' +
                ", errorRate=" + errorRate +
                '}';
    }

    public Double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(Double errorRate) {
        this.errorRate = errorRate;
    }

    public CustomerFaultDto(Long id, Long customerId, Long questionId, Long examId, Date attempTime, String title, Double errorRate) {
        super(id, customerId, questionId, examId, attempTime);
        this.title = title;
        this.errorRate = errorRate;
    }

    public CustomerFaultDto(String title, Double errorRate) {
        this.title = title;
        this.errorRate = errorRate;
    }

    public CustomerFaultDto() {
    }

    public CustomerFaultDto(Long id, Long customerId, Long questionId, Long examId, Date attempTime, String title) {
        super(id, customerId, questionId, examId, attempTime);
        this.title = title;
    }

    public CustomerFaultDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
