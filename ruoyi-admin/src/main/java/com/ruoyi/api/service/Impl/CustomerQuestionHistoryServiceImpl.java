package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.CustomerQuestionHistory;
import com.ruoyi.api.domain.Options;
import com.ruoyi.api.domain.dto.CustomerQuestionHistoryDto;
import com.ruoyi.api.mapper.CustomerDtoMapper;
import com.ruoyi.api.mapper.CustomerQuestionHistoryMapper;
import com.ruoyi.api.service.CustomerQuestionHistoryService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.QuestionHistory.domain.QuestionHistory;
import com.ruoyi.web.controller.customerFault.domain.CustomerFault;
import com.ruoyi.web.controller.customerFault.mapper.CustomerFaultMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lemon-zhu119
 * @date 2025-03-10
 */
@Service
public class CustomerQuestionHistoryServiceImpl implements CustomerQuestionHistoryService {
    /**  */
    @Autowired
    private CustomerQuestionHistoryMapper customerQuestionHistoryMapper;
    /**  */
    @Autowired
    private CustomerFaultMapper customerFaultMapper;
    /**  */
    @Autowired
    private CustomerDtoMapper customerDtoMapper;

    /**
     * @param now
     * @param customerFault
     * @param customerFaultMapper
     */
    static void updateQuestionHistory(Date now, CustomerFault customerFault, CustomerFaultMapper customerFaultMapper) {
        List<CustomerFault> customerFaultList = customerFaultMapper.selectCustomerFaultList(customerFault);
        customerFault.setAttempTime(now);
        if (customerFaultList.isEmpty()) {
            customerFaultMapper.insertCustomerFault(customerFault);
        } else {
            customerFault.setId(customerFaultList.get(0).getId());
            customerFaultMapper.updateCustomerFault(customerFault);
        }
    }

    /**
     * @param customerId
     * @param type
     * @return
     */
    @Override
    public List<CustomerQuestionHistory> selectListByCustomerId(Long customerId, String type) {
        List<CustomerQuestionHistory> customerQuestionHistoryList;
        customerQuestionHistoryList = customerQuestionHistoryMapper.selectQuestionsHistoryList(customerId, type);
        return getCustomerQuestionHistories(customerId, customerQuestionHistoryList);
    }

    /**
     * @param optionStr
     * @return
     */// 处理 "A:1;B:2;C:3" 格式
    private List<Options> parseOptionsMethod1(String optionStr) {
        List<Options> options = new ArrayList<>();
        Pattern pattern = Pattern.compile("([A-D]):[^;]*");
        Matcher matcher = pattern.matcher(optionStr);
        int i = 0;
        String[] index = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        while (matcher.find()) {
            Options option = new Options();
            String optionList = matcher.group().trim();
            optionList = optionList.substring(2);
            option.setValue(index[i]);
            option.setName(optionList);
            options.add(option);
            i++;
        }
        return options;
    }

    /**
     * @param optionStr
     * @return
     */// 处理 "1;2;3" 格式
    private List<Options> parseOptionsMethod2(String optionStr) {
        List<Options> options = new ArrayList<>();
        String[] optionArray = optionStr.split(";");
        String[] index = new String[]{"A", "B", "C", "D", "E", "F", "G"};

        for (int i = 0; i < optionArray.length; i++) {
            Options option = new Options();
            option.setValue(index[i]);
            option.setName(optionArray[i].trim());
            options.add(option);
        }
        return options;
    }

    /**
     * @param customerQuestionHistoryDto
     * @return 用户顺序练习记录对象question_history
     */
    @Override
    public QuestionHistory handleToQuestionHistory(CustomerQuestionHistoryDto customerQuestionHistoryDto) {
        QuestionHistory questionHistory = new QuestionHistory();
        BeanUtils.copyProperties(customerQuestionHistoryDto, questionHistory);
        questionHistory.setQuestionId(questionHistory.getId());
        questionHistory.setId(null);
        questionHistory.setIsAnswered("1");
        if (customerQuestionHistoryDto.getCustomerAnswer().equals(customerQuestionHistoryDto.getAnswer())) {
            questionHistory.setAnswerCorrect("1");
        } else {
            questionHistory.setAnswerCorrect("0");
            Date now = DateUtils.getNowDate();
            CustomerFault customerFault = new CustomerFault(null, customerQuestionHistoryDto.getCustomerId(), questionHistory.getQuestionId(), null, null);
            updateQuestionHistory(now, customerFault, customerFaultMapper);
        }
        return questionHistory;
    }

    /**
     * @param customerId
     * @return
     */
    @Override
    public List<CustomerQuestionHistory> selectNullTypeListByCustomerId(Long customerId) {
        List<CustomerQuestionHistory> customerQuestionHistoryList = customerQuestionHistoryMapper.selectNullTypeQuestionsHistoryList(customerId);
        return getCustomerQuestionHistories(customerId, customerQuestionHistoryList);
    }

    /**
     * @param customerId
     * @param customerQuestionHistoryList
     * @return
     */
    private List<CustomerQuestionHistory> getCustomerQuestionHistories(Long customerId, List<CustomerQuestionHistory> customerQuestionHistoryList) {
        List<Long> questionIdList = customerDtoMapper.selectCustomerQuestionIdServer(customerId);
        for (CustomerQuestionHistory customerQuestionHistory : customerQuestionHistoryList) {
            List<Options> optionsMethod1 = parseOptionsMethod1(customerQuestionHistory.getOption());
            List<Options> optionsMethod2 = parseOptionsMethod2(customerQuestionHistory.getOption());
            // 选择结果更好的解析方法
            List<Options> options = optionsMethod1.size() > 0 ? optionsMethod1 : optionsMethod2;
            customerQuestionHistory.setOptions(options);

            String[] answers = customerQuestionHistory.getAnswer().split(";");

            // 判断题目类型
            if (options.size() > 2) {
                customerQuestionHistory.setType(answers.length > 1 ? "多选题" : "选择题");
            } else {
                //目前所有题目都是选择题
                customerQuestionHistory.setType("选择题");
            }

            //判断是否收藏
            if (questionIdList.contains(customerQuestionHistory.getId())) {
                customerQuestionHistory.setFavorite(true);
            }
        }
        return customerQuestionHistoryList;
    }
}
