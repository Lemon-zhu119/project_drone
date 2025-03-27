package com.ruoyi.api.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class CityDto {
    private Integer id;
    private String label;
    private Integer provinceId;
    private Integer value;


    public CityDto(Integer id, String label, Integer provinceId, Integer value) {
        this.id = id;
        this.label = label;
        this.provinceId = provinceId;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public CityDto() {
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", provinceId=" + provinceId +
                ", value=" + value +
                '}';
    }
}
