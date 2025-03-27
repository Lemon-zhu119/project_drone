package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.CityDto;
import com.ruoyi.api.domain.dto.CommentLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentLikeMapper {




    CommentLike selectCommentAndCustomer(@Param("commentId") Integer commentId,@Param("customerId") Integer customerId);

    int insert(CommentLike exitLike);

    void deleteById(Integer id);

    boolean exitBycustomerIdAndCommentId(@Param("commentId") Integer commentId,@Param("customerId") Integer customerId);

    Integer getActuallyCount(Integer commentId);
}
