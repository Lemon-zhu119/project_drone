package com.ruoyi.web.controller.ExamPaper.domain;

import java.util.List;

/**
 * @author Lemon-zhu119
 * @date 2025-03-13
 */
public class ExamPaperType {
    /** 试卷id*/
    private Long examPaperId;
    /** 题目类型+数量 */
    private List<TypeDto> typeConfigs;

    /**
     *
     */
    public ExamPaperType() {
    }

    /**
     * @param examPaperId
     * @param typeConfigs
     */
    public ExamPaperType(Long examPaperId, List<TypeDto> typeConfigs) {
        this.examPaperId = examPaperId;
        this.typeConfigs = typeConfigs;
    }

    /**
     * @return
     */
    public Long getExamPaperId() {
        return examPaperId;
    }

    /**
     * @param examPaperId
     */
    public void setExamPaperId(Long examPaperId) {
        this.examPaperId = examPaperId;
    }

    /**
     * @return
     */
    public List<TypeDto> getTypeConfigs() {
        return typeConfigs;
    }

    /**
     * @param typeConfigs
     */
    public void setTypeConfigs(List<TypeDto> typeConfigs) {
        this.typeConfigs = typeConfigs;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "ExamPaperType{" +
                "examPaperId=" + examPaperId +
                ", typeDtoList=" + typeConfigs +
                '}';
    }

}
