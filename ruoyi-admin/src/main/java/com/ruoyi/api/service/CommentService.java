package com.ruoyi.api.service;

import com.ruoyi.api.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {


    List<CommentDto> selectCommentList(Integer insId,Integer customerId);

    int addcomment(CommentDto commentDto);

    boolean praise(Integer commentId, Integer customerId);

    Integer deleteById(Integer commentId);
}
