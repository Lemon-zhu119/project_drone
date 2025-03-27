package com.ruoyi.web.controller.institutionmore.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 机构信息详情管理对象 institution
 *
 * @author ruoyi
 * @date 2024-12-16
 */
public class Institution extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer id;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String companyname;

    /** 基地 */
    @Excel(name = "基地")
    private String base;

    /** 办公室地址 */
    @Excel(name = "办公室地址")
    private String office;

    /** 统一社会信用代码 */
    @Excel(name = "统一社会信用代码")
    private String creditcode;

    /** 法人名称 */
    @Excel(name = "法人名称")
    private String legalperson;

    /**  项目负责人 */
    @Excel(name = " 项目负责人")
    private String responsibleperson;

    /** 项目负责人联系电话 */
    @Excel(name = "项目负责人联系电话")
    private String phone;

    /** 评分 */
    @Excel(name = "评分")
    private Double score;
    @Excel(name = "排序序号")
    private Integer orderId;
    @Excel(name ="经度")
    private Double latitude;
    private Double longitude;
    private String introduce;
    private Integer customerId;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCreditcode() {
        return creditcode;
    }

    public void setCreditcode(String creditcode) {
        this.creditcode = creditcode;
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

    public String getResponsibleperson() {
        return responsibleperson;
    }

    public void setResponsibleperson(String responsibleperson) {
        this.responsibleperson = responsibleperson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Institution(Integer id, String province, String companyname, String base, String office, String creditcode, String legalperson, String responsibleperson, String phone, Double score, Integer orderId, Double latitude, Double longitude, String introduce, Integer customerId, String url) {
        this.id = id;
        this.province = province;
        this.companyname = companyname;
        this.base = base;
        this.office = office;
        this.creditcode = creditcode;
        this.legalperson = legalperson;
        this.responsibleperson = responsibleperson;
        this.phone = phone;
        this.score = score;
        this.orderId = orderId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.introduce = introduce;
        this.customerId = customerId;
        this.url = url;
    }

    public Institution() {
    }

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", companyname='" + companyname + '\'' +
                ", base='" + base + '\'' +
                ", office='" + office + '\'' +
                ", creditcode='" + creditcode + '\'' +
                ", legalperson='" + legalperson + '\'' +
                ", responsibleperson='" + responsibleperson + '\'' +
                ", phone='" + phone + '\'' +
                ", score=" + score +
                ", orderId=" + orderId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", introduce='" + introduce + '\'' +
                ", customerId=" + customerId +
                ", url='" + url + '\'' +
                '}';
    }
}
