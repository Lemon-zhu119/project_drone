package com.ruoyi.web.controller.customerFault.service.impl;

import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.CustomerQuestionHistory;
import com.ruoyi.api.domain.dto.CustomerFaultDto;
import com.ruoyi.api.mapper.CustomerExamHistoryMapper;
import com.ruoyi.api.mapper.CustomerQuestionHistoryMapper;
import com.ruoyi.web.controller.customer.mapper.CustomerMapper;
import com.ruoyi.web.controller.customer.service.ICustomerService;
import com.ruoyi.web.controller.customerFault.domain.CustomerFault;
import com.ruoyi.web.controller.customerFault.mapper.CustomerFaultMapper;
import com.ruoyi.web.controller.customerFault.service.ICustomerFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户错题表Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-16
 */
@Service
public class CustomerFaultServiceImpl implements ICustomerFaultService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerFaultMapper customerFaultMapper;
    @Autowired
    private CustomerQuestionHistoryMapper customerQuestionHistoryMapper;
    @Autowired
    private CustomerExamHistoryMapper customerExamHistoryMapper;

    /**
     * 查询用户错题表
     *
     * @param id 用户错题表主键
     * @return 用户错题表
     */
    @Override
    public CustomerFault selectCustomerFaultById(Integer id) {
        return customerFaultMapper.selectCustomerFaultById(id);
    }

    /**
     * 查询用户错题表列表
     *
     * @param customerFault 用户错题表
     * @return 用户错题表
     */
    @Override
    public List<CustomerFault> selectCustomerFaultList(CustomerFault customerFault) {
        return customerFaultMapper.selectCustomerFaultList(customerFault);
    }

    /**
     * 新增用户错题表
     *
     * @param customerFault 用户错题表
     * @return 结果
     */
    @Override
    public int insertCustomerFault(CustomerFault customerFault) {
        return customerFaultMapper.insertCustomerFault(customerFault);
    }

    /**
     * 修改用户错题表
     *
     * @param customerFault 用户错题表
     * @return 结果
     */
    @Override
    public int updateCustomerFault(CustomerFault customerFault) {
        return customerFaultMapper.updateCustomerFault(customerFault);
    }

    /**
     * 批量删除用户错题表
     *
     * @param ids 需要删除的用户错题表主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFaultByIds(Integer[] ids) {
        return customerFaultMapper.deleteCustomerFaultByIds(ids);
    }

    /**
     * 删除用户错题表信息
     *
     * @param id 用户错题表主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFaultById(Integer id) {
        return customerFaultMapper.deleteCustomerFaultById(id);
    }

    @Override
    public List<CustomerFaultDto> selectCustomerFaultDtoList(Long customerId) {
        List<CustomerFaultDto> customerFaultDtoList=customerFaultMapper.selectCustomerFaultDtoList(customerId);
        List<CustomerQuestionHistory> customerQuestionHistoryList=customerQuestionHistoryMapper.selectQuestionsHistoryList(customerId,null);
        Long AnswerNumber=customerMapper.selectAnswerNumberByCustomerId(customerId);
        double faultNumber= customerFaultDtoList.size();
        double questionNumber=customerQuestionHistoryList.size() + AnswerNumber;
        double errorRate =faultNumber*100/questionNumber;
        if(errorRate==100){
            errorRate =faultNumber*50 /questionNumber;
        }
        errorRate = Math.round(errorRate * 100.0) / 100.0;
        List<CustomerFaultDto> sortedList= customerFaultDtoList.stream().sorted(Comparator.comparing(CustomerFaultDto::getQuestionId)).collect(Collectors.toList());
        sortedList.get(0).setErrorRate(errorRate);
        return sortedList;
    }
}
