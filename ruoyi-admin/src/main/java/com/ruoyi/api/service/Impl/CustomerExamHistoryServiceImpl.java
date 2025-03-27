package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.ExamPaperHistory;
import com.ruoyi.api.domain.Options;
import com.ruoyi.api.domain.dto.CustomerPaperScoreDto;
import com.ruoyi.api.mapper.CustomerDtoMapper;
import com.ruoyi.api.mapper.CustomerExamHistoryMapper;
import com.ruoyi.api.service.CustomerExamHistoryService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import com.ruoyi.web.controller.ExamPaper.mapper.ExamPaperMapper;
import com.ruoyi.web.controller.ExamPaperDetail.domain.ExamPaperDetail;
import com.ruoyi.web.controller.ExamPaperDetail.mapper.ExamPaperDetailMapper;
import com.ruoyi.web.controller.customer.domain.Customer;
import com.ruoyi.web.controller.customer.mapper.CustomerMapper;
import com.ruoyi.web.controller.customerExamPaper.domain.CustomerPaper;
import com.ruoyi.web.controller.customerExamPaper.mapper.CustomerPaperMapper;
import com.ruoyi.web.controller.customerFault.domain.CustomerFault;
import com.ruoyi.web.controller.customerFault.mapper.CustomerFaultMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Lemon-zhu119
 * @date 2025-03-12
 */
@Service
public class CustomerExamHistoryServiceImpl implements CustomerExamHistoryService {

    /**  */
    @Autowired
    private CustomerExamHistoryMapper customerExamHistoryMapper;
    /**  */
    @Autowired
    private CustomerFaultMapper customerFaultMapper;
    /**  */
    @Autowired
    private CustomerDtoMapper customerDtoMapper;
    /**  */
    @Autowired
    private ExamPaperDetailMapper examPaperDetailMapper;
    /**  */
    @Autowired
    private ExamPaperMapper examPaperMapper;

    /**  */
    @Autowired
    private CustomerPaperMapper customerPaperMapper;
    /**  */
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * @param id
     * @return
     */
    @Override
    public List<CustomerExamHistory> selectQuestionsByExamPaperId(Integer id) {
        return customerExamHistoryMapper.selectQuestionsByExamPaperId(id);
    }

    /**
     * @param customerId
     * @param examPaperId
     * @return
     */
    @Override
    public List<CustomerExamHistory> selectExamPaperListByCustomerId(Long customerId, Long examPaperId) {
        List<CustomerExamHistory> list = customerExamHistoryMapper.selectExamPaperHistoryByCustomerId(customerId, examPaperId);
        List<Long> questionIdList = customerDtoMapper.selectCustomerQuestionIdServer(customerId);

        for (CustomerExamHistory history : list) {
            List<Options> optionsMethod1 = parseOptionsMethod1(history.getOption());
            List<Options> optionsMethod2 = parseOptionsMethod2(history.getOption());

            // 选择结果更好的解析方法
            List<Options> options = optionsMethod1.size() > 0 ? optionsMethod1 : optionsMethod2;
            history.setOptions(options);

            String[] answer = history.getAnswer().split(";");
            if (options.size() == 2) {
                history.setType("判断题");
            } else if (options.size() > 2 && answer.length > 1) {
                history.setType("多选题");
            } else {
                history.setType("选择题");
            }

            if (questionIdList.contains(history.getId())) {
                history.setFavorite(true);
            }
        }
        return list;
    }

    /**
     * @param optionStr
     * @return
     */// 处理 "A:1;B:2;C:3" 格式
    private List<Options> parseOptionsMethod1(String optionStr) {
        List<Options> options = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(optionStr, ";");
        while (tokenizer.hasMoreTokens()) {
            String pair = tokenizer.nextToken();
            String[] parts = pair.split(":");
            if (parts.length == 2) {
                String value = parts[0].trim();
                String name = parts[1].trim();
                options.add(new Options(value, name));
            }
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
            options.add(new Options(index[i], optionArray[i].trim()));
        }
        return options;
    }

    /**
     * @param customerId
     * @return
     *///获取全部考试卷的成绩历史
    @Override
    public List<CustomerPaperScoreDto> selectCustomerPaperScoreHistory(Long customerId) {
        //获取全部试卷
        List<CustomerPaper> customerPaperList= customerPaperMapper.selectCustomerPaperList(new CustomerPaper(null,customerId,null,null,null));
        //将全部的试卷筛选用户有做过的历史成绩进行计算
        return getAllSore(customerPaperList, customerId);
    }

    /**
     * @param customerExamHistory
     * @return
     */
    @Override
    public ExamPaperHistory handleToExamPaperHistory(CustomerExamHistory customerExamHistory) {
        ExamPaperHistory examPaperHistory = new ExamPaperHistory();
        BeanUtils.copyProperties(customerExamHistory, examPaperHistory);
        examPaperHistory.setExamPaperId(customerExamHistory.getExamId());
        examPaperHistory.setQuestionId(examPaperHistory.getId());
        examPaperHistory.setId(null);
        examPaperHistory.setIsAnswered("1");
        if (examPaperHistory.getCustomerAnswer().equals(customerExamHistory.getAnswer())) {
            examPaperHistory.setAnswerCorrect("1");
        } else {
            examPaperHistory.setAnswerCorrect("0");
            Date now = DateUtils.getNowDate();
            CustomerFault customerFault = new CustomerFault(null, customerExamHistory.getCustomerId(), customerExamHistory.getId(), null, null);
            CustomerQuestionHistoryServiceImpl.updateQuestionHistory(now, customerFault, customerFaultMapper);
        }
        return examPaperHistory;
    }

    /**
     * @param customerId
     * @param examPaperId
     * @param i
     * @return boolean
     *///通过获取正确的成绩来更新总分
    @Override
    public boolean calculateScore(Long customerId, Long examPaperId, Integer i) {
        List<Double> allCorrectScore = customerPaperMapper.selectScoreList(customerId, examPaperId);
        Double nowScore = allCorrectScore.stream().reduce(0.0, Double::sum);
        List<CustomerPaper> customerPaperDB=customerPaperMapper.selectCustomerPaperList(new CustomerPaper(null, customerId, examPaperId,null));
        CustomerPaper customerPaper;
        if(customerPaperDB.isEmpty()){
            return false;
        }else {
            customerPaper=MaxTimeRecordCustomerPaper(customerPaperDB);
        }
        if(i!=null){
            customerPaper.setStatus(1);
        }
        customerPaper.setScore(nowScore);
        return customerPaperMapper.updateCustomerPaper(customerPaper) > 0;
    }

    /**
     * @param customerPaperDB
     * @return 用户考卷记录对象customer_paper
     *///通过排序返回时间最晚的考试记录
    private CustomerPaper MaxTimeRecordCustomerPaper(List<CustomerPaper> customerPaperDB) {
        CustomerPaper latestPaper = customerPaperDB.get(0);
        for (CustomerPaper customerPaper : customerPaperDB) {
            if (customerPaper.getCreateTime().after(latestPaper.getCreateTime())) {
                latestPaper = customerPaper;
            }
        }
        return latestPaper;
    }

    //处理更新
//    private boolean handleCustomerPaper(CustomerPaper customerPaper) {
//        return ;
//    }

    /**
     * @param customerPaperList
     * @param customerId
     * @return
     *///获取全部试卷的总分和当前分数
    private List<CustomerPaperScoreDto> getAllSore(List<CustomerPaper> customerPaperList, Long customerId) {
        List<CustomerPaperScoreDto> customerPaperScoreDtoList = new ArrayList<>();
        for (CustomerPaper customerPaper : customerPaperList) {
            //总分
            Double totalScore = handleTotalSore(customerPaper.getExamPaperId());
            //为了获取试卷名称
            ExamPaper examPaper=examPaperMapper.selectExamPaperById(customerPaper.getExamPaperId());
            CustomerPaperScoreDto customerPaperScoreDto;
            Customer customer=customerMapper.selectCustomerById(customerPaper.getCustomerId());
            if(customer==null||examPaper==null){
                continue;
            }
            if(customerId==null){
                customerPaperScoreDto=new CustomerPaperScoreDto(examPaper.getId(),customerPaper.getCustomerId(),examPaper.getId(),totalScore,customerPaper.getStatus(),examPaper.getTitle(),customerPaper.getScore(),customer.getUsername());
            }else{
                customerPaperScoreDto= new CustomerPaperScoreDto(examPaper.getId(), customerId, examPaper.getId(), totalScore, examPaper.getTitle(), customerPaper.getScore(),customerPaper.getStatus());
            }
            if(customerPaperScoreDto.getStatus()==1){
                customerPaperScoreDtoList.add(customerPaperScoreDto);
            }
        }
        return customerPaperScoreDtoList;
    }

    /**
     * @param examPaperId
     * @return
     *///通过题目的绑定关系计算当前试卷的总分
    private Double handleTotalSore(Long examPaperId) {
        Double totalScore = 0.0;
        List<ExamPaperDetail> examPaperDetailList = examPaperDetailMapper.selectExamPaperDetailList(new ExamPaperDetail(null, null, examPaperId, null, null, null));
        for (ExamPaperDetail examPaperDetail : examPaperDetailList) {
            totalScore += examPaperDetail.getScore();
        }
        if (totalScore == 0.0) {
            totalScore = 100.0;
        }
        return totalScore;
    }
}
