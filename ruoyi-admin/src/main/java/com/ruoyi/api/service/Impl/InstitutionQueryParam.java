package com.ruoyi.api.service.Impl;

public class InstitutionQueryParam {
    private String city;
    private String subject;
    private String keyword;
    private Double latitude;
    private Double longitude;

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public InstitutionQueryParam setCity(String city) {
        this.city = city;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public InstitutionQueryParam setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public InstitutionQueryParam setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public InstitutionQueryParam setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public InstitutionQueryParam setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
}
