package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.InstitutionData;
import com.ruoyi.api.domain.dto.CustomerFavourDto;
import com.ruoyi.api.mapper.CustomerDtoMapper;
import com.ruoyi.api.service.CustomerDtoServer;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDtoServerImpl implements CustomerDtoServer {

    @Autowired
    private CustomerDtoMapper customerDtoMapper;
    //此处可改变返回的类
    @Override
    public List<CustomerFavourDto> selectCustomerQuestionFavourServer(Integer customerId) {
        return customerDtoMapper.selectCustomerQuestionServer(customerId);
    }

    @Override
    public List<InstitutionData> selectCustomerInstitutionFavourServer(Integer customerId) {
        return customerDtoMapper.selectCustomerInstitutionServer(customerId);
    }

    @Override
    public List<ExamPaper> selectCustomerExamPaperFavourServer(Integer customerId) {
        return customerDtoMapper.selectCustomerExamPaperServer(customerId);
    }
}
