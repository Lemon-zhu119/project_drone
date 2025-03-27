package com.ruoyi.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Recruitment {

    private Integer id;
    private String title;
    private String salary;
    private String education;
    private String experience;
    private String tags;
    private Integer institutionId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
    private Integer status;
    private String companyName;
    private String location;
    private String companyLogo;
    private List<String> tagList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }



    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Recruitment() {
    }

    public Recruitment(Integer id, String title, String salary, String education, String experience, String tags, Integer institutionId, Date publishDate, Integer status, String companyName, String location, String companyLogo, List<String> tagList) {
        this.id = id;
        this.title = title;
        this.salary = salary;
        this.education = education;
        this.experience = experience;
        this.tags = tags;
        this.institutionId = institutionId;
        this.publishDate = publishDate;
        this.status = status;
        this.companyName = companyName;
        this.location = location;
        this.companyLogo = companyLogo;
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "Recruitment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary='" + salary + '\'' +
                ", education='" + education + '\'' +
                ", experience='" + experience + '\'' +
                ", tags='" + tags + '\'' +
                ", institutionId=" + institutionId +
                ", publishDate=" + publishDate +
                ", status=" + status +
                ", companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", tagList=" + tagList +
                '}';
    }
}
