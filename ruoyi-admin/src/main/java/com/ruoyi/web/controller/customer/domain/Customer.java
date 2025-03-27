package com.ruoyi.web.controller.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户信息管理对象 customer
 *
 * @author ruoyi
 * @date 2024-12-11
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String username;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 密码 */
    @Excel(name = "密码")
    private String password;
    @Excel(name = "头像")
    private String avatar;
    @Excel(name="在线状态")
    private Integer online;
    @Excel(name="标签")
    private String tag;
    @Excel(name="答题数")
    private Long answerNumber;

    public Long getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Long answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Customer(Long id, String username, String phone, String email, String address, String password, String avatar, Integer online, String tag, Long answerNumber) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.password = password;
        this.avatar = avatar;
        this.online = online;
        this.tag = tag;
        this.answerNumber = answerNumber;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", online=" + online +
                ", tag='" + tag + '\'' +
                ", answerNumber=" + answerNumber +
                "} " + super.toString();
    }
}
