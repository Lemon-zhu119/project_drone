package com.ruoyi.api.domain.dto;

import com.ruoyi.web.controller.customerfavor.domain.CustomerFavor;

import java.util.Date;

public class CustomerFavourDto extends CustomerFavor {
    private String title;
    private String content;



    @Override
    public String toString() {
        return "CustomerFavourDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                "} " + super.toString();
    }

    public CustomerFavourDto(Integer id, Integer questionId, Integer examPaperId, Integer institutionId, Integer customerId, Date favouriteTime) {
        super(id, questionId, examPaperId, institutionId, customerId, favouriteTime);
    }

    public CustomerFavourDto(Integer id, Integer questionId, Integer examPaperId, Integer institutionId, Integer customerId, Date favouriteTime, String title, String content) {
        super(id, questionId, examPaperId, institutionId, customerId, favouriteTime);
        this.title = title;
        this.content = content;
    }

    public CustomerFavourDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public CustomerFavourDto() {
    }

    public CustomerFavourDto(Integer id, Integer questionId, Integer examPaperId, Integer institutionId, Integer customerId) {
        super(id, questionId, examPaperId, institutionId, customerId);
    }

    public CustomerFavourDto(Integer id, Integer questionId, Integer examPaperId, Integer institutionId, Integer customerId, String title, String content) {
        super(id, questionId, examPaperId, institutionId, customerId);
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
