package com.ruoyi.api.domain.dto;

import java.util.Date;

public class CommentLike {
    private Integer id;
    private Integer customerId;
    private Integer commentId;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public CommentLike(Integer id, Integer customerId, Integer commentId, Date createTime, Date updateTime) {
        this.id = id;
        this.customerId = customerId;
        this.commentId = commentId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public CommentLike() {
    }

    @Override
    public String toString() {
        return "CommentLike{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", commentId=" + commentId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
