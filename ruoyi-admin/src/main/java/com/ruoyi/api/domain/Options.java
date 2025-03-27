package com.ruoyi.api.domain;

public class Options {
    private String value;
    private String name;

    public Options(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public Options() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Options{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
