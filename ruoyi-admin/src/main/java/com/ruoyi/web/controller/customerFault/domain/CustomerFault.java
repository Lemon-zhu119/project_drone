package com.ruoyi.web.controller.customerFault.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 用户错题表对象 customer_fault
 * 
 * @author ruoyi
 * @date 2024-12-16
 */
public class CustomerFault extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long customerId;

    /** 题目id */
    @Excel(name = "题目id")
    private Long questionId;

    /** 考卷id */
    @Excel(name = "考卷id")
    private Long examId;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private Date attempTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }
    public void setExamId(Long examId) 
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
    }
    public void setAttempTime(Date attempTime)
    {
        this.attempTime = attempTime;
    }

    public Date getAttempTime()
    {
        return attempTime;
    }

    public CustomerFault(Long id, Long customerId, Long questionId, Long examId, Date attempTime) {
        this.id = id;
        this.customerId = customerId;
        this.questionId = questionId;
        this.examId = examId;
        this.attempTime = attempTime;
    }

    @Override
    public String toString() {
        return "CustomerFault{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", questionId=" + questionId +
                ", examId=" + examId +
                ", attempTime=" + attempTime +
                '}';
    }

    public CustomerFault() {
    }

}
