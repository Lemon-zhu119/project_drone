package com.ruoyi.api.domain.dto;

import java.util.Date;

public class SmsRecord {
    private Long id;
    private String phone;
    private String code;
    private String type;
    private String ip;
    private Integer status;
    private String errorMsg;
    private String createBy;
    private Date createTime;
    private Date updateTime;
    private Date expireTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public SmsRecord(Long id, String phone, String code, String type, String ip, Integer status, String errorMsg, String createBy, Date createTime, Date updateTime, Date expireTime) {
        this.id = id;
        this.phone = phone;
        this.code = code;
        this.type = type;
        this.ip = ip;
        this.status = status;
        this.errorMsg = errorMsg;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.expireTime = expireTime;
    }

    public SmsRecord() {
    }

    @Override
    public String toString() {
        return "SmsRecord{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", errorMsg='" + errorMsg + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", expireTime=" + expireTime +
                '}';
    }
}
