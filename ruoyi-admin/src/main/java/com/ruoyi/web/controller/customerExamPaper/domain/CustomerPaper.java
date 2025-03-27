package com.ruoyi.web.controller.customerExamPaper.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户考卷记录对象 customer_paper
 * 
 * @author ruoyi
 * @date 2024-12-19
 */
public class CustomerPaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long customerId;

    /** 考卷id */
    @Excel(name = "考卷id")
    private Long examPaperId;

    /** 分数 */
    @Excel(name = "分数")
    private Double score;

    /** 状态 */
    @Excel(name = "是否提交")
    private Integer status;

    @Override
    public String toString() {
        return "CustomerPaper{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", examPaperId=" + examPaperId +
                ", score=" + score +
                ", status=" + status +
                "} " + super.toString();
    }

    public CustomerPaper(Long id, Long customerId, Long examPaperId, Double score, Integer status) {
        this.id = id;
        this.customerId = customerId;
        this.examPaperId = examPaperId;
        this.score = score;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CustomerPaper() {
    }

    public CustomerPaper(Long id, Long customerId, Long examPaperId, Double score) {
        this.id = id;
        this.customerId = customerId;
        this.examPaperId = examPaperId;
        this.score = score;
    }

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
    public void setExamPaperId(Long examPaperId)
    {
        this.examPaperId = examPaperId;
    }

    public Long getExamPaperId()
    {
        return examPaperId;
    }
    public void setScore(Double score)
    {
        this.score = score;
    }

    public Double getScore()
    {
        return score;
    }

}
