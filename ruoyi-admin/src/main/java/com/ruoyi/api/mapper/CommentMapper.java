package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {


    List<CommentDto> selectCommentList(@Param("insId") Integer insId);

    int addcomment(CommentDto commentDto);

    int updateNum(Integer commentId);

    void decrementNum(Integer commentId);

    CommentDto selectComentById(Integer commentId);

    void updateAgreeNum(@Param("commentId") Integer commentId, @Param("Num") Integer NUm);

    Integer delete(Integer commentId);
}
