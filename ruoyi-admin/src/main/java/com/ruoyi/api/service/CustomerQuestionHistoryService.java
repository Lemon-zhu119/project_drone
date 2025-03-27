package com.ruoyi.api.service;

import com.ruoyi.api.domain.CustomerQuestionHistory;
import com.ruoyi.api.domain.dto.CustomerQuestionHistoryDto;
import com.ruoyi.web.controller.QuestionHistory.domain.QuestionHistory;

import java.util.List;

public interface CustomerQuestionHistoryService {


    List<CustomerQuestionHistory> selectListByCustomerId(Long customerId,String type);

    QuestionHistory handleToQuestionHistory(CustomerQuestionHistoryDto customerQuestionHistoryDto);

    List<CustomerQuestionHistory> selectNullTypeListByCustomerId(Long customerId);
}
