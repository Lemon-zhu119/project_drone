package com.ruoyi.web.controller.ExamPaper.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.web.controller.ExamPaperDetail.mapper.ExamPaperDetailMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 考卷管理对象 exam_paper
 *
 * @author ruoyi
 * @date 2024-12-14
 */
public class ExamPaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 考卷描述 */
    @Excel(name = "考卷描述")
    private String description;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;
    private Integer duration;
    /** 判断是否有题目的绑定 */
    private boolean haveDetail;

    public boolean isHaveDetail() {
        return haveDetail;
    }

    public void setHaveDetail(boolean haveDetail) {
        this.haveDetail = haveDetail;
    }

    public ExamPaper(Long id, String title, String description, Date creatTime, Integer duration, boolean haveDetail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatTime = creatTime;
        this.duration = duration;
        this.haveDetail = haveDetail;
    }

    public ExamPaper(Long id, String title, String description, Date creatTime, Integer duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatTime = creatTime;
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ExamPaper(Long id, String title, String description, Date creatTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatTime = creatTime;
    }

    public ExamPaper() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "ExamPaper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creatTime=" + creatTime +
                ", duration=" + duration +
                ", haveDetail=" + haveDetail +
                "} " + super.toString();
    }
}
