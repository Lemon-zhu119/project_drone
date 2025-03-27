package com.ruoyi.api.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class QualificationDto {
    private String phone;
    private String filePath;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public QualificationDto() {
    }

    public QualificationDto(String phone, String filePath) {
        this.phone = phone;
        this.filePath=filePath;
    }

    @Override
    public String toString() {
        return "QualificationDto{" +
                "phone='" + phone + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
