package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.CustomerQuestionHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerQuestionHistoryMapper {


    List<CustomerQuestionHistory> selectQuestionsHistoryList(@Param("customerId") Long customerId,@Param("type") String type);

    List<CustomerQuestionHistory> selectNullTypeQuestionsHistoryList(@Param("customerId") Long customerId);
}
