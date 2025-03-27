package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.dto.CustomerPaperScoreDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerExamHistoryMapper {


    List<CustomerExamHistory> selectQuestionsByExamPaperId(Integer id);

    List<CustomerExamHistory> selectExamPaperListByCustomerId(@Param("customerId") Long customerId,@Param("examPaperId") Long examPaperId);
    List<CustomerExamHistory> selectExamPaperHistoryByCustomerId(@Param("customerId") Long customerId,@Param("examPaperId") Long examPaperId);

    List<CustomerPaperScoreDto> selectCustomerPaperScoreHistory(@Param("customerId") Long customerId);
}
