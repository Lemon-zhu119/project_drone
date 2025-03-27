package com.ruoyi.web.controller.QuestionHistory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户顺序练习记录对象 question_history
 *
 * @author ruoyi
 * @date 2024-12-14
 */
public class QuestionHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 考卷id */
    @Excel(name = "用户id")
    private Long customerId;

    /** 考题id */
    @Excel(name = "考题id")
    private Long questionId;

    /** 是否回答 */
    @Excel(name = "是否回答")
    private String isAnswered;

    /** 是否正确 */
    @Excel(name = "是否正确")
    private String answerCorrect;

    /** 用户答案 */
    @Excel(name = "用户答案")
    private String customerAnswer;

    @Override
    public String toString() {
        return "QuestionHistory{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", questionId=" + questionId +
                ", isAnswered='" + isAnswered + '\'' +
                ", answerCorrect='" + answerCorrect + '\'' +
                ", customerAnswer='" + customerAnswer + '\'' +
                '}';
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setQuestionId(Long questionId)
    {
        this.questionId = questionId;
    }

    public Long getQuestionId()
    {
        return questionId;
    }
    public void setIsAnswered(String isAnswered)
    {
        this.isAnswered = isAnswered;
    }

    public String getIsAnswered()
    {
        return isAnswered;
    }
    public void setAnswerCorrect(String answerCorrect)
    {
        this.answerCorrect = answerCorrect;
    }

    public String getAnswerCorrect()
    {
        return answerCorrect;
    }
    public void setCustomerAnswer(String customerAnswer)
    {
        this.customerAnswer = customerAnswer;
    }

    public String getCustomerAnswer()
    {
        return customerAnswer;
    }

    public QuestionHistory(Long id, Long customerId, Long questionId, String isAnswered, String answerCorrect, String customerAnswer) {
        this.id = id;
        this.customerId = customerId;
        this.questionId = questionId;
        this.isAnswered = isAnswered;
        this.answerCorrect = answerCorrect;
        this.customerAnswer = customerAnswer;
    }

    public QuestionHistory() {
    }

}
