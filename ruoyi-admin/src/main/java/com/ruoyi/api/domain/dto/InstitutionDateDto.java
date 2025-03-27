package com.ruoyi.api.domain.dto;

import com.ruoyi.api.domain.Course;
import com.ruoyi.api.domain.InstitutionData;

import java.util.List;

public class InstitutionDateDto extends InstitutionData {

    private Double latitude;
    private Double longitude;
    public Double distance;
    public String province;
    public String introduce;

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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public InstitutionDateDto(String area, Integer id, String companyName, Integer score, String insPic, Integer orderId, String institutionAddress, String creditCode, String legalPerson, String phone, String responsiblePerson, String insTag, List<String> tags, Integer number, List<String> courseTags, List<Course> courses, Double latitude, Double longitude, Double distance, String province, String introduce) {
        super(area, id, companyName, score, insPic, orderId, institutionAddress, creditCode, legalPerson, phone, responsiblePerson, insTag, tags, number, courseTags, courses);
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.province = province;
        this.introduce = introduce;
    }

    public InstitutionDateDto(Double latitude, Double longitude, Double distance, String province, String introduce) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.province = province;
        this.introduce = introduce;
    }

    public InstitutionDateDto(String area, Integer id, String companyName, Integer score, String insPic, Integer orderId, String institutionAddress, String creditCode, String legalPerson, String phone, String responsiblePerson, String insTag, List<String> tags, Integer number, List<String> courseTags, List<Course> courses) {
        super(area, id, companyName, score, insPic, orderId, institutionAddress, creditCode, legalPerson, phone, responsiblePerson, insTag, tags, number, courseTags, courses);
    }

    public InstitutionDateDto() {
    }
}
