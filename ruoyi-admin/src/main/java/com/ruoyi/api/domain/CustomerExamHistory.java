package com.ruoyi.api.domain;

import java.util.Date;
import java.util.List;

public class CustomerExamHistory extends CustomerQuestionHistory {
    private Long examId;
    private Integer Duration;
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public CustomerExamHistory(Long id, String type, String title, String option, List<Options> options, String answer, Double score, Long currentQuestionNumber, String customerAnswer, String isAnswered, Long answerCorrect, boolean favorite, Long customerId, Long examId, Integer duration, String customerName) {
        super(id, type, title, option, options, answer, score, currentQuestionNumber, customerAnswer, isAnswered, answerCorrect, favorite, customerId);
        this.examId = examId;
        Duration = duration;
        this.customerName = customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }



    public CustomerExamHistory() {
    }

    public CustomerExamHistory(Long id, String type, String title, String option, List<Options> options, String answer, Double score, Long current_question_number, String customerAnswer, String isAnswered, Long answerCorrect, boolean favorite, Long customerId, Long examId) {
        super(id, type, title, option, options, answer, score, current_question_number, customerAnswer, isAnswered, answerCorrect, favorite, customerId);
        this.examId = examId;
    }

    public CustomerExamHistory(Long id, String type, String title, String option, List<Options> options, String answer, Double score, Long current_question_number, String customerAnswer, String isAnswered, Long answerCorrect, boolean favorite, Long examId) {
        super(id, type, title, option, options, answer, score, current_question_number, customerAnswer, isAnswered, answerCorrect, favorite);
        this.examId = examId;
    }

    public CustomerExamHistory(Long id, String type, String title, String option, List<Options> options, String answer, Double score, Long current_question_number, String customerAnswer, String isAnswered, Long answerCorrect, Long examId) {
        super(id, type, title, option, options, answer, score, current_question_number, customerAnswer, isAnswered, answerCorrect);
        this.examId = examId;
    }

    public CustomerExamHistory(String type, String title, String option, List<Options> options, String answer, Double score, Long current_question_number, String customerAnswer, String isAnswered, Long answerCorrect, Long examId) {
        super(type, title, option, options, answer, score, current_question_number, customerAnswer, isAnswered, answerCorrect);
        this.examId = examId;
    }

    public CustomerExamHistory(Long examId) {
        this.examId = examId;
    }
}
