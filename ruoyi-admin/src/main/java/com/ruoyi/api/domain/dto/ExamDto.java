package com.ruoyi.api.domain.dto;

import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;

import java.util.Date;

/**
 * @author Lemon-zhu119
 * @date 2025-03-09
 */
public class ExamDto extends ExamPaper{
    /** 当前做到的题数*/
    private Long knowHistory;
    /** 总共多少题 */
    private Long totalQuestion;
    /** 是否进行中 */
    private boolean isInProgress;

    /** 开始时间 */
    private Date createTime;
    /** 提交状态 */
    private int status;

    /**
     * @return
     */
    @Override
    public String toString() {
        return "ExamDto{" +
                "knowHistory=" + knowHistory +
                ", totalQuestion=" + totalQuestion +
                ", isInProgress=" + isInProgress +
                ", createTime=" + createTime +
                ", status=" + status +
                "} " + super.toString();
    }

    public ExamDto(Long id, String title, String description, Date creatTime, Integer duration, Long knowHistory, Long totalQuestion, boolean isInProgress, Date createTime, int status) {
        super(id, title, description, creatTime, duration);
        this.knowHistory = knowHistory;
        this.totalQuestion = totalQuestion;
        this.isInProgress = isInProgress;
        this.createTime = createTime;
        this.status = status;
    }

    public ExamDto(Long id, String title, String description, Date creatTime, Long knowHistory, Long totalQuestion, boolean isInProgress, Date createTime, int status) {
        super(id, title, description, creatTime);
        this.knowHistory = knowHistory;
        this.totalQuestion = totalQuestion;
        this.isInProgress = isInProgress;
        this.createTime = createTime;
        this.status = status;
    }

    public ExamDto(Long knowHistory, Long totalQuestion, boolean isInProgress, Date createTime, int status) {
        this.knowHistory = knowHistory;
        this.totalQuestion = totalQuestion;
        this.isInProgress = isInProgress;
        this.createTime = createTime;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return
     */
    public Long getKnowHistory() {
        return knowHistory;
    }

    /**
     * @param knowHistory
     */
    public void setKnowHistory(Long knowHistory) {
        this.knowHistory = knowHistory;
    }

    /**
     * @return
     */
    public Long getTotalQuestion() {
        return totalQuestion;
    }

    /**
     * @param totalQuestion
     */
    public void setTotalQuestion(Long totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    /**
     * @return boolean
     */
    public boolean isInProgress() {
        return isInProgress;
    }

    /**
     * @param inProgress
     */
    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
    }


    /**
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @param knowHistory
     * @param totalQuestion
     * @param isInProgress
     * @param createTime
     */
    public ExamDto(Long knowHistory, Long totalQuestion, boolean isInProgress, Date createTime) {
        this.knowHistory = knowHistory;
        this.totalQuestion = totalQuestion;
        this.isInProgress = isInProgress;

        this.createTime = createTime;
    }

    /**
     * @param id
     * @param title
     * @param description
     * @param creatTime
     * @param knowHistory
     * @param totalQuestion
     * @param isInProgress
     * @param createTime
     */
    public ExamDto(Long id, String title, String description, Date creatTime, Long knowHistory, Long totalQuestion, boolean isInProgress,Date createTime) {
        super(id, title, description, creatTime);
        this.knowHistory = knowHistory;
        this.totalQuestion = totalQuestion;
        this.isInProgress = isInProgress;
        this.createTime = createTime;
    }

    /**
     *
     */
    public ExamDto() {
    }

    /**
     * @param id
     * @param title
     * @param description
     * @param creatTime
     */
    public ExamDto(Long id, String title, String description, Date creatTime) {
        super(id, title, description, creatTime);
    }

}
