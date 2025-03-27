package com.ruoyi.api.domain.dto;

import java.util.Date;
import java.util.List;

public class CommentDto {
    private Integer id;
    private Integer customerId;
    private Integer institutionId;
    private String customerName;
    private String customerImg;
    private String comment;
    private String commentImg;
    private List<String> commentImgs;
    private String customerAddress;
    private String tag;
    private List<String> tags;
    private Integer agreeNum;
    private Date createTime;
    private Integer serviceScore;      // 服务好评分
    private Integer feeScore;         // 收费透明评分
    private Integer coachScore;    // 教练服务评分
    private Double averageScore;
    private Integer newStatus;

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentImg() {
        return commentImg;
    }

    public void setCommentImg(String commentImg) {
        this.commentImg = commentImg;
    }

    public List<String> getCommentImgs() {
        return commentImgs;
    }

    public void setCommentImgs(List<String> commentImgs) {
        this.commentImgs = commentImgs;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(Integer agreeNum) {
        this.agreeNum = agreeNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(Integer serviceScore) {
        this.serviceScore = serviceScore;
    }

    public Integer getFeeScore() {
        return feeScore;
    }

    public void setFeeScore(Integer feeScore) {
        this.feeScore = feeScore;
    }

    public Integer getCoachScore() {
        return coachScore;
    }

    public void setCoachScore(Integer coachScore) {
        this.coachScore = coachScore;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }


    public CommentDto() {
    }

    public CommentDto(Integer id, Integer customerId, Integer institutionId, String customerName, String customerImg, String comment, String commentImg, List<String> commentImgs, String customerAddress, String tag, List<String> tags, Integer agreeNum, Date createTime, Integer serviceScore, Integer feeScore, Integer coachScore, Double averageScore, Integer newStatus) {
        this.id = id;
        this.customerId = customerId;
        this.institutionId = institutionId;
        this.customerName = customerName;
        this.customerImg = customerImg;
        this.comment = comment;
        this.commentImg = commentImg;
        this.commentImgs = commentImgs;
        this.customerAddress = customerAddress;
        this.tag = tag;
        this.tags = tags;
        this.agreeNum = agreeNum;
        this.createTime = createTime;
        this.serviceScore = serviceScore;
        this.feeScore = feeScore;
        this.coachScore = coachScore;
        this.averageScore = averageScore;
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", institutionId=" + institutionId +
                ", customerName='" + customerName + '\'' +
                ", customerImg='" + customerImg + '\'' +
                ", comment='" + comment + '\'' +
                ", commentImg='" + commentImg + '\'' +
                ", commentImgs=" + commentImgs +
                ", customerAddress='" + customerAddress + '\'' +
                ", tag='" + tag + '\'' +
                ", tags=" + tags +
                ", agreeNum=" + agreeNum +
                ", createTime=" + createTime +
                ", serviceScore=" + serviceScore +
                ", feeScore=" + feeScore +
                ", coachScore=" + coachScore +
                ", averageScore=" + averageScore +

                '}';
    }
}
