package com.ruoyi.api.domain;

import java.util.List;

public class Course {
    private Integer id;
    private String name;
    private String tag;
    private List<String> tags;
    private String subject;
    private Integer expense;
    private String coursePic;

    public Course(Integer id, String name, String tag, List<String> tags, String subject, Integer expense, String coursePic) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.tags = tags;
        this.subject = subject;
        this.expense = expense;
        this.coursePic = coursePic;
    }

    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", tags=" + tags +
                ", subject='" + subject + '\'' +
                ", expense=" + expense +
                ", coursePic='" + coursePic + '\'' +
                '}';
    }
}
