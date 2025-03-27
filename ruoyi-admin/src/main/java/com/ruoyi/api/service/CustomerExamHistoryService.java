package com.ruoyi.api.service;

import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.ExamPaperHistory;
import com.ruoyi.api.domain.dto.CustomerPaperScoreDto;

import java.util.List;

public interface CustomerExamHistoryService {


    List<CustomerExamHistory> selectQuestionsByExamPaperId(Integer id);

    List<CustomerExamHistory> selectExamPaperListByCustomerId(Long customerId, Long examPaperId);

    List<CustomerPaperScoreDto> selectCustomerPaperScoreHistory(Long customerId);

    ExamPaperHistory handleToExamPaperHistory(CustomerExamHistory customerExamHistory);

    boolean calculateScore(Long customerId, Long examPaperId, Integer i);
}
