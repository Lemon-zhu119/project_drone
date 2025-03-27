package com.ruoyi.web.controller.ExamPaper.domain;

/**
 * @author Lemon-zhu119
 * @date 2025-03-13
 */
public class TypeDto {
    /** 题目类型 */
    private String type;
    /** 数量 */
    private Long count;

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     *
     */
    public TypeDto() {
    }

    /**
     * @param type
     * @param count
     */
    public TypeDto(String type, Long count) {
        this.type = type;
        this.count = count;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "TypeDto{" +
                "type='" + type + '\'' +
                ", count=" + count +
                '}';
    }
}
