package com.ruoyi.api.domain.dto;


import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private Long id;           // 用户ID
    private String oldPassword; // 原密码
    private String newPassword; // 新密码
}
