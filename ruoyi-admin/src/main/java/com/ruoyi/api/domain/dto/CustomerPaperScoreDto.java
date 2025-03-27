package com.ruoyi.api.domain.dto;

import com.ruoyi.web.controller.customerExamPaper.domain.CustomerPaper;

public class CustomerPaperScoreDto extends CustomerPaper {
    private String paperName;
    private Double nowScore;
    private String customerName;


    public CustomerPaperScoreDto(Long id, Long customerId, Long examPaperId, Double score, String paperName, Double nowScore, Integer status) {
        super(id, customerId, examPaperId, score, status);
        this.paperName = paperName;
        this.nowScore = nowScore;
    }

    public CustomerPaperScoreDto(Long id, Long customerId, Long examPaperId, Double score, Integer status, String paperName, Double nowScore, String customerName) {
        super(id, customerId, examPaperId, score, status);
        this.paperName = paperName;
        this.nowScore = nowScore;
        this.customerName = customerName;
    }

    public CustomerPaperScoreDto(String paperName, Double nowScore, String customerName) {
        this.paperName = paperName;
        this.nowScore = nowScore;
       this.customerName=customerName;
    }

    public CustomerPaperScoreDto(Long id, Long customerId, Long examPaperId, Double score, String paperName, Double nowScore) {
        super(id, customerId, examPaperId, score);
        this.paperName = paperName;
        this.nowScore = nowScore;
    }

    @Override
    public String toString() {
        return "CustomerPaperScoreDto{" +
                "paperName='" + paperName + '\'' +
                ", nowScore=" + nowScore +
                ", customerName='" + customerName + '\'' +
                '}'+ super.toString();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getNowScore() {
        return nowScore;
    }

    public void setNowScore(Double nowScore) {
        this.nowScore = nowScore;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public CustomerPaperScoreDto() {
    }

    public CustomerPaperScoreDto(Long id, Long customerId, Long examPaperId, Double score) {
        super(id, customerId, examPaperId, score);
    }

    public CustomerPaperScoreDto(String paperName) {
        this.paperName = paperName;
    }

    public CustomerPaperScoreDto(Long id, Long customerId, Long examPaperId, Double score, String paperName) {
        super(id, customerId, examPaperId, score);
        this.paperName = paperName;
    }

    public CustomerPaperScoreDto(Long id, Long customerId, Long examPaperId, Double score, String paperName, Double nowScore, String customerName) {
        super(id, customerId, examPaperId, score);
        this.paperName = paperName;
        this.nowScore = nowScore;
        this.customerName = customerName;
    }
}
