package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.InstitutionData;
import com.ruoyi.api.domain.dto.CustomerFavourDto;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import com.ruoyi.web.controller.question.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerDtoMapper {
    List<CustomerFavourDto> selectCustomerQuestionServer(@Param("customerId") Integer customerId);

    List<InstitutionData> selectCustomerInstitutionServer(@Param("customerId") Integer customerId);

    List<ExamPaper> selectCustomerExamPaperServer(@Param("customerId") Integer customerId);

    List<Long> selectCustomerQuestionIdServer(@Param("customerId") Long customerId);
}
