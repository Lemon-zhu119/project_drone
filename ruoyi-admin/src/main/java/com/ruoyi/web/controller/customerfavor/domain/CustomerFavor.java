package com.ruoyi.web.controller.customerfavor.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 用户收藏对象 customer_favor
 * 
 * @author ruoyi
 * @date 2024-12-19
 */
public class CustomerFavor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer id;

    /** 题目id */
    @Excel(name = "题目id")
    private Integer questionId;

    /** 考卷id */
    @Excel(name = "考卷id")
    private Integer examPaperId;

    /** 机构id */
    @Excel(name = "机构id")
    private Integer institutionId;

    /** 用户id */
    @Excel(name = "用户id")
    private Integer customerId;
    private Date favouriteTime;

    @Override
    public String toString() {
        return "CustomerFavor{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", examPaperId=" + examPaperId +
                ", institutionId=" + institutionId +
                ", customerId=" + customerId +
                ", favouriteTime=" + favouriteTime +
                "} " + super.toString();
    }

    public Date getFavouriteTime() {
        return favouriteTime;
    }

    public void setFavouriteTime(Date favouriteTime) {
        this.favouriteTime = favouriteTime;
    }

    public CustomerFavor(Integer id, Integer questionId, Integer examPaperId, Integer institutionId, Integer customerId, Date favouriteTime) {
        this.id = id;
        this.questionId = questionId;
        this.examPaperId = examPaperId;
        this.institutionId = institutionId;
        this.customerId = customerId;
        this.favouriteTime = favouriteTime;
    }

    public CustomerFavor() {
    }

    public CustomerFavor(Integer id, Integer questionId, Integer examPaperId, Integer institutionId, Integer customerId) {
        this.id = id;
        this.questionId = questionId;
        this.examPaperId = examPaperId;
        this.institutionId = institutionId;
        this.customerId = customerId;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setQuestionId(Integer questionId) 
    {
        this.questionId = questionId;
    }

    public Integer getQuestionId() 
    {
        return questionId;
    }
    public void setExamPaperId(Integer examPaperId) 
    {
        this.examPaperId = examPaperId;
    }

    public Integer getExamPaperId() 
    {
        return examPaperId;
    }
    public void setInstitutionId(Integer institutionId) 
    {
        this.institutionId = institutionId;
    }

    public Integer getInstitutionId() 
    {
        return institutionId;
    }
    public void setCustomerId(Integer customerId) 
    {
        this.customerId = customerId;
    }

    public Integer getCustomerId() 
    {
        return customerId;
    }

}
