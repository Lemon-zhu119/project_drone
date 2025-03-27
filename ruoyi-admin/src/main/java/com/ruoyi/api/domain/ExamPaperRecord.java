package com.ruoyi.api.domain;

import java.util.Date;

public class ExamPaperRecord {
    private Long id;
    private Long examPaperId;
    private Long customerId;
    public Date startTime;

    public Long getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Long examPaperId) {
        this.examPaperId = examPaperId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ExamPaperRecord{" +
                "id=" + id +
                ", examPaperId=" + examPaperId +
                ", customerId=" + customerId +
                ", startTime=" + startTime +
                '}';
    }

    public ExamPaperRecord(Long id, Long examPaperId, Long customerId, Date startTime) {
        this.id = id;
        this.examPaperId = examPaperId;
        this.customerId = customerId;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public ExamPaperRecord() {
    }

    public ExamPaperRecord(Long id, Date startTime) {
        this.id = id;
        this.startTime = startTime;
    }
}
