package com.ruoyi.api.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class Province extends BaseEntity {
    private Integer id;
    private String label;
    private Integer value;
    private List<CityDto> children;

    public Province(Integer id, String label, Integer value, List<CityDto> children) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.children = children;
    }

    public Province() {
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<CityDto> getChildren() {
        return children;
    }

    public void setChildren(List<CityDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", value=" + value +
                ", children=" + children +
                '}';
    }
}
