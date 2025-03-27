package com.ruoyi.api.service;

import com.ruoyi.api.domain.InstitutionData;
import com.ruoyi.api.domain.dto.CustomerFavourDto;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerDtoServer {
    List<CustomerFavourDto> selectCustomerQuestionFavourServer(Integer customerId);

    List<InstitutionData> selectCustomerInstitutionFavourServer(Integer customerId);

    List<ExamPaper> selectCustomerExamPaperFavourServer(Integer customerId);
}
