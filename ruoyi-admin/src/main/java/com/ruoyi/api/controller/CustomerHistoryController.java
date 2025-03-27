package com.ruoyi.api.controller;

import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.ExamPaperHistory;
import com.ruoyi.api.service.CustomerExamHistoryService;
import com.ruoyi.api.service.ExamPaperHistoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.controller.customer.domain.Customer;
import com.ruoyi.web.controller.customer.service.ICustomerService;
import com.ruoyi.web.controller.customerExamPaper.domain.CustomerPaper;
import com.ruoyi.web.controller.customerExamPaper.service.ICustomerPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lemon-zhu119
 * @date 2025-03-11
 */
@RestController
@RequestMapping("/api/history")
public class CustomerHistoryController extends BaseController {
    /**  */
    @Autowired
    private CustomerExamHistoryService customerExamHistoryService;
    /**  */
    @Autowired
    private ExamPaperHistoryService examPaperHistoryService;
    /**  */
    @Autowired
    private ICustomerPaperService customerPaperService;
    /**  */
    @Autowired
    private ICustomerService customerService;

    /**  */
    private String filePath = System.getProperty("user.dir") + "/files/";

    /**
     * @param examPaperHistory
     * @return 操作消息提醒
     */
    @PostMapping("/examlist")
    public AjaxResult ListExamHistory(@RequestBody ExamPaperHistory examPaperHistory) {
        List<CustomerExamHistory> list = customerExamHistoryService.selectExamPaperListByCustomerId(examPaperHistory.getCustomerId(), examPaperHistory.getExamPaperId());
        for (CustomerExamHistory customerExamHistory : list) {
            customerExamHistory.setExamId(examPaperHistory.getExamPaperId());
        }
        return AjaxResult.success(list);
    }


    /**
     * @param customerPaper
     * @return 试卷开始答题添加记录
     */
    @PostMapping("/insert/startTime")
    public AjaxResult insertStartTime(@RequestBody CustomerPaper customerPaper) {
        int insert = customerPaperService.insertCustomerPaper(customerPaper);
        if (insert==1) {
            return AjaxResult.success("插入新纪录成功");
        } else {
            return AjaxResult.error("插入新记录失败");
        }
    }

    /**
     * @param customerExamHistory
     * @return 操作消息提醒
     */
    @PostMapping("/add/questionExamHistory")
    @ResponseBody
    public AjaxResult addQuestionExamHistory(@RequestBody CustomerExamHistory customerExamHistory) {
        ExamPaperHistory ExamPaperHistory = customerExamHistoryService.handleToExamPaperHistory(customerExamHistory);
        ExamPaperHistory examPaperHistoryDB = new ExamPaperHistory(null, customerExamHistory.getCustomerId(), null, customerExamHistory.getExamId(), null, customerExamHistory.getId(), null, null, null);
        List<ExamPaperHistory> examPaperHistoryList = examPaperHistoryService.selectQuestionHistoryList(examPaperHistoryDB);
        if (examPaperHistoryList.isEmpty()) {
            int insert = examPaperHistoryService.insert(ExamPaperHistory);
            if (insert == 1) {
                Long answerNumber=customerService.getAnswerNumber(customerExamHistory.getCustomerId());
                if(answerNumber==null){
                    answerNumber=0L;
                }
                answerNumber++;
                Customer customer=new Customer();
                customer.setAnswerNumber(answerNumber);
                customerService.updateCustomer(customer);
                //更新得分
                if(customerExamHistoryService.calculateScore(customerExamHistory.getCustomerId(), customerExamHistory.getExamId(), null)){
                    return AjaxResult.success("插入成功并更新总分成功");
                }else {
                    return AjaxResult.error("插入失败");
                }
            } else {
                return AjaxResult.error("插入失败");
            }
        } else {
            int update = examPaperHistoryService.update(ExamPaperHistory);
            if (update == 1) {
                return AjaxResult.success("更新成功");
            } else {
                return AjaxResult.error("更新失败");
            }
        }
    }

    /**
     * @param customerId
     * @return 操作消息提醒
     *///获取全部的考试记录
    @GetMapping("/examScoreHistory")
    public AjaxResult getCustomerPaperScoreHistory(@RequestParam(required = false) Long customerId) {
        return AjaxResult.success(customerExamHistoryService.selectCustomerPaperScoreHistory(customerId));
    }

    /**
     * @param customerId
     * @param examId
     * @return 操作消息提醒
     */
    @GetMapping("/del/examHistory")
    public AjaxResult delExamHistory(@RequestParam Long customerId,
                                     @RequestParam Long examId) {
        int del = examPaperHistoryService.deleteExamHistoryByExamId(customerId, examId);
        if (del == 1) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }

    /**
     * @param customerId
     * @param examId
     * @return 操作消息提醒
     */
    @GetMapping("/calculateScore")
    public AjaxResult calculateScoreEnd(@RequestParam Long customerId,
                                 @RequestParam Long examId) {
        if(customerExamHistoryService.calculateScore(customerId,examId,1)){
            return AjaxResult.success("试卷提交成功");
        }else {
            return AjaxResult.error("系统错误，请联系管理员");
        }
    }

    /**
     * 用于后期获取最近一张试卷的提交
     * @param customerPaperList
     * @return 最大的id
     */
    private CustomerPaper MaxIdOne(List<CustomerPaper> customerPaperList) {
        CustomerPaper maxCustomerPaper=new CustomerPaper();
        Long maxId=customerPaperList.get(0).getId();
        for(CustomerPaper customerPaper:customerPaperList){
            if(customerPaper.getId()>maxId){
                maxCustomerPaper=customerPaper;
            }
        }
        return maxCustomerPaper;
    }
    //根据用户id和试卷id去统计成绩
//    private boolean calculateScore(Long customerId,Long examId){
//        List<CustomerPaperScoreDto> customerPaperScoreDtoList=customerExamHistoryService.selectCustomerPaperScoreHistory(customerId);
//        for(CustomerPaperScoreDto customerPaperScoreDto:customerPaperScoreDtoList){
//            if(customerPaperScoreDto.getExamPaperId()==examId){
//                CustomerPaper customerPaper=new CustomerPaper(null,customerId,examId,customerPaperScoreDto.getNowScore());
//                if(customerPaperService.insertCustomerPaper(customerPaper)>0){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
