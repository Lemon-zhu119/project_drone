package com.ruoyi.api.domain;
import com.ruoyi.common.core.domain.BaseEntity;

public class ExamPaperHistory extends BaseEntity {
    private Long id;
    private Long customerId;
    private Long currentQuestionNumber;
    private Long examPaperId;
    private String answerCorrect;
    private Long questionId;
    private String isAnswered;
    private Long status;
    private String customerAnswer;


    public ExamPaperHistory() {
    }

    @Override
    public String toString() {
        return "ExamPaperHistory{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", currentQuestionNumber=" + currentQuestionNumber +
                ", examPaperId=" + examPaperId +
                ", answerCorrect='" + answerCorrect + '\'' +
                ", questionId=" + questionId +
                ", isAnswered='" + isAnswered + '\'' +
                ", status=" + status +
                ", customerAnswer='" + customerAnswer + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public void setCurrentQuestionNumber(Long currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public Long getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Long examPaperId) {
        this.examPaperId = examPaperId;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(String isAnswered) {
        this.isAnswered = isAnswered;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(String customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    public ExamPaperHistory(Long id, Long customerId, Long currentQuestionNumber, Long examPaperId, String answerCorrect, Long questionId, String isAnswered, Long status, String customerAnswer) {
        this.id = id;
        this.customerId = customerId;
        this.currentQuestionNumber = currentQuestionNumber;
        this.examPaperId = examPaperId;
        this.answerCorrect = answerCorrect;
        this.questionId = questionId;
        this.isAnswered = isAnswered;
        this.status = status;
        this.customerAnswer = customerAnswer;
    }
}
