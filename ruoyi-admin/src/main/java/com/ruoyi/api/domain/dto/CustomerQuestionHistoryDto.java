package com.ruoyi.api.domain.dto;
import com.ruoyi.api.domain.CustomerQuestionHistory;
import com.ruoyi.api.domain.Options;

import java.util.List;

public class CustomerQuestionHistoryDto extends CustomerQuestionHistory {
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public CustomerQuestionHistoryDto() {
    }


    @Override
    public String toString() {
        return "CustomerQuestionHistoryDto{" +
                "customerId=" + customerId +
                '}';
    }

    public CustomerQuestionHistoryDto(Long id, String type, String title, String option, List<Options> options, String answer, Double score, Long current_question_number, String customerAnswer, String isAnswered, Long answerCorrect, Long customerId) {
        super(id, type, title, option, options, answer, score, current_question_number, customerAnswer, isAnswered, answerCorrect);
        this.customerId = customerId;
    }

    public CustomerQuestionHistoryDto(String type, String title, String option, List<Options> options, String answer, Double score, Long current_question_number, String customerAnswer, String isAnswered, Long answerCorrect, Long customerId) {
        super(type, title, option, options, answer, score, current_question_number, customerAnswer, isAnswered, answerCorrect);
        this.customerId = customerId;
    }

    public CustomerQuestionHistoryDto(Long customerId) {
        this.customerId = customerId;
    }
}
