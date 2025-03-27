package com.ruoyi.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class QualificationRecord {
    private Integer id;
    private Integer institutionId;
    private String fileUrl;
    private String fileType;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public QualificationRecord(Integer id, Integer institutionId, String fileUrl, String fileType, String phone, Date uploadTime, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.institutionId = institutionId;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.phone = phone;
        this.uploadTime = uploadTime;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public QualificationRecord() {
    }

    @Override
    public String toString() {
        return "QualificationRecord{" +
                "id=" + id +
                ", institutionId=" + institutionId +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileType='" + fileType + '\'' +
                ", phone='" + phone + '\'' +
                ", uploadTime=" + uploadTime +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
