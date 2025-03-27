package com.ruoyi.api.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.List;

public class CustomerQuestionHistory extends BaseEntity {
    private Long id;
    private String type;
    private String title;
    private String option;
    private List<Options> options;
    private String answer;
    private Double score;
    private Long currentQuestionNumber;

    private String customerAnswer;
    private String isAnswered;
    private Long answerCorrect;
    private boolean favorite;
    private Long customerId;

    public CustomerQuestionHistory(Long id, String type, String title, String option, List<Options> options, String answer, Double score, Long currentQuestionNumber, String customerAnswer, String isAnswered, Long answerCorrect, boolean favorite, Long customerId) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.option = option;
        this.options = options;
        this.answer = answer;
        this.score = score;
        this.currentQuestionNumber = currentQuestionNumber;
        this.customerAnswer = customerAnswer;
        this.isAnswered = isAnswered;
        this.answerCorrect = answerCorrect;
        this.favorite = favorite;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CustomerQuestionHistory{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", option='" + option + '\'' +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                ", score='" + score + '\'' +
                ", currentQuestionNumber=" + currentQuestionNumber +
                ", customerAnswer='" + customerAnswer + '\'' +
                ", isAnswered='" + isAnswered + '\'' +
                ", answerCorrect='" + answerCorrect + '\'' +
                ", favorite=" + favorite +
                ", customerId=" + customerId +
                '}';
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public CustomerQuestionHistory(Long id, String type, String title, String option, List<Options> options, String answer, Double score, Long currentQuestionNumber, String customerAnswer, String isAnswered, Long answerCorrect, boolean favorite) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.option = option;
        this.options = options;
        this.answer = answer;
        this.score = score;
        this.currentQuestionNumber = currentQuestionNumber;
        this.customerAnswer = customerAnswer;
        this.isAnswered = isAnswered;
        this.answerCorrect = answerCorrect;
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerQuestionHistory(Long id, String type, String title, String option, List<Options> options, String answer, Double score, Long currentQuestionNumber, String customerAnswer, String isAnswered, Long answerCorrect) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.option = option;
        this.options = options;
        this.answer = answer;
        this.score = score;
        this.currentQuestionNumber = currentQuestionNumber;
        this.customerAnswer = customerAnswer;
        this.isAnswered = isAnswered;
        this.answerCorrect = answerCorrect;
    }

    public Long getCurrent_question_number() {
        return currentQuestionNumber;
    }

    public void setCurrent_question_number(Long currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public String getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(String isAnswered) {
        this.isAnswered = isAnswered;
    }

    public Long getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(Long answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public String getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(String customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOption() {
        return option;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public CustomerQuestionHistory(String type, String title, String option, List<Options> options, String answer, Double score, Long currentQuestionNumber,
                                   String customerAnswer, String isAnswered, Long answerCorrect) {
        this.type = type;
        this.title = title;
        this.option = option;
        this.options = options;
        this.answer = answer;
        this.score = score;
        this.currentQuestionNumber = currentQuestionNumber;
        this.customerAnswer = customerAnswer;
        this.isAnswered = isAnswered;
        this.answerCorrect = answerCorrect;
    }

    public CustomerQuestionHistory() {
    }


}
