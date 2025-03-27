package com.ruoyi.web.controller.ExamPaperDetail.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考卷管理对象 exam_paper
 * 
 * @author ruoyi
 * @date 2024-12-14
 */
public class ExamPaperDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 标题 */
    @Excel(name = "绑定的题目id")
    private Long questionId;

    /** 考卷描述 */
    @Excel(name = "考卷id")
    private Long examPaperId;
    private Long questionOrderId;
    @Excel(name = "题目标签")
    private String tag;
    @Excel(name = "每题分数")
    private Double score;

    @Override
    public String toString() {
        return "ExamPaperDetail{" +
                "id=" + id +
                ", questionId='" + questionId + '\'' +
                ", examPaperId='" + examPaperId + '\'' +
                ", questionOrderId=" + questionOrderId +
                ", tag='" + tag + '\'' +
                ", score=" + score +
                "} " + super.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Long examPaperId) {
        this.examPaperId = examPaperId;
    }

    public Long getQuestionOrderId() {
        return questionOrderId;
    }

    public void setQuestionOrderId(Long questionOrderId) {
        this.questionOrderId = questionOrderId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ExamPaperDetail() {
    }

    public ExamPaperDetail(Long id, Long questionId, Long examPaperId, Long questionOrderId, String tag, Double score) {
        this.id = id;
        this.questionId = questionId;
        this.examPaperId = examPaperId;
        this.questionOrderId = questionOrderId;
        this.tag = tag;
        this.score = score;
    }
}
