package com.ruoyi.api.domain;

import com.ruoyi.api.domain.Course;

import java.util.List;

public class InstitutionData {
    private String area;
    private Integer id;
    private String companyName;

    private Integer score;
    private String insPic;
    private Integer orderId;
    private String institutionAddress;
    private String creditCode;
    private String legalPerson;
    private String phone;
    private String responsiblePerson;
    private String insTag;
    private List<String> tags;
    private Integer number;
    private List<String> courseTags;

    private List<Course> courses;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getInsPic() {
        return insPic;
    }

    public void setInsPic(String insPic) {
        this.insPic = insPic;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getInsTag() {
        return insTag;
    }

    public void setInsTag(String insTag) {
        this.insTag = insTag;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<String> getCourseTags() {
        return courseTags;
    }

    public void setCourseTags(List<String> courseTags) {
        this.courseTags = courseTags;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public InstitutionData(String area, Integer id, String companyName, Integer score, String insPic, Integer orderId, String institutionAddress, String creditCode, String legalPerson, String phone, String responsiblePerson, String insTag, List<String> tags, Integer number, List<String> courseTags, List<Course> courses) {
        this.area = area;
        this.id = id;
        this.companyName = companyName;
        this.score = score;
        this.insPic = insPic;
        this.orderId = orderId;
        this.institutionAddress = institutionAddress;
        this.creditCode = creditCode;
        this.legalPerson = legalPerson;
        this.phone = phone;
        this.responsiblePerson = responsiblePerson;
        this.insTag = insTag;
        this.tags = tags;
        this.number = number;
        this.courseTags = courseTags;
        this.courses = courses;
    }

    public InstitutionData() {
    }

    @Override
    public String toString() {
        return "InstitutionData{" +
                "area='" + area + '\'' +
                ", id=" + id +
                ", companyName='" + companyName + '\'' +
                ", score=" + score +
                ", insPic='" + insPic + '\'' +
                ", orderId=" + orderId +
                ", institutionAddress='" + institutionAddress + '\'' +
                ", creditCode='" + creditCode + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", phone='" + phone + '\'' +
                ", responsiblePerson='" + responsiblePerson + '\'' +
                ", insTag='" + insTag + '\'' +
                ", tags=" + tags +
                ", number=" + number +
                ", courseTags=" + courseTags +
                ", courses=" + courses +
                '}';
    }
}
