package com.ruoyi.api.domain.dto;

import java.util.Date;
import java.util.List;

public class CommentVo extends CommentDto{
    private boolean Liked;

    public boolean isLiked() {
        return Liked;
    }

    public void setLiked(boolean liked) {
        Liked = liked;
    }

    public CommentVo(boolean liked) {
        Liked = liked;
    }

    public CommentVo(Integer id, Integer customerId, Integer institutionId, String customerName, String customerImg, String comment, String commentImg, List<String> commentImgs, String customerAddress, String tag, List<String> tags, Integer agreeNum, Date createTime, Integer serviceScore, Integer feeScore, Integer coachScore, Double averageScore, Integer newStatus, boolean liked) {
        super(id, customerId, institutionId, customerName, customerImg, comment, commentImg, commentImgs, customerAddress, tag, tags, agreeNum, createTime, serviceScore, feeScore, coachScore, averageScore, newStatus);
        Liked = liked;
    }

    public CommentVo() {
    }

    public CommentVo(Integer id, Integer customerId, Integer institutionId, String customerName, String customerImg, String comment, String commentImg, List<String> commentImgs, String customerAddress, String tag, List<String> tags, Integer agreeNum, Date createTime, Integer serviceScore, Integer feeScore, Integer coachScore, Double averageScore, Integer newStatus) {
        super(id, customerId, institutionId, customerName, customerImg, comment, commentImg, commentImgs, customerAddress, tag, tags, agreeNum, createTime, serviceScore, feeScore, coachScore, averageScore, newStatus);
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "Liked=" + Liked +
                '}';
    }
}
