package com.ruoyi.web.controller.question.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 题目管理对象 question
 *
 * @author ruoyi
 * @date 2024-12-11
 */
public class Question extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String content;

    /** 答案 */
    @Excel(name = "答案")
    private String answer;

    /** 难度 */
    @Excel(name = "类型")
    private String type;

    /** 来源 */
    @Excel(name = "分数")
    private Double score;
    @Excel(name = "选项")
    private String option;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Question(Long id, String content, String answer, String type, Double score, String option) {
        this.id = id;
        this.content = content;
        this.answer = answer;
        this.type = type;
        this.score = score;
        this.option = option;
    }

    public Question() {
    }
    public Question(String type) {
        this.type=type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", answer='" + answer + '\'' +
                ", type='" + type + '\'' +
                ", score='" + score + '\'' +
                ", option='" + option + '\'' +
                '}';
    }
}
