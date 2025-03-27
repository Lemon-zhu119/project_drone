package com.ruoyi.api.controller;

import com.ruoyi.api.common.Result;
import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.CustomerQuestionHistory;
import com.ruoyi.api.domain.ExamPaperHistory;
import com.ruoyi.api.domain.dto.CustomerQuestionHistoryDto;
import com.ruoyi.api.domain.dto.ExamDto;
import com.ruoyi.api.service.CustomerExamHistoryService;
import com.ruoyi.api.service.CustomerQuestionHistoryService;
import com.ruoyi.api.service.ExamDtoService;
import com.ruoyi.api.service.ExamPaperHistoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.controller.ExamPaper.domain.TypeDto;
import com.ruoyi.web.controller.QuestionHistory.domain.QuestionHistory;
import com.ruoyi.web.controller.QuestionHistory.service.IQuestionHistoryService;
import com.ruoyi.web.controller.question.domain.Question;
import com.ruoyi.web.controller.question.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lemon-zhu119
 * @date 2025-03-09
 */
@RestController
@RequestMapping("/api/question")
public class QuestionDataController extends BaseController {
    /**  */
    @Autowired
    private IQuestionService questionService;

    /**  */
    @Autowired
    private ExamPaperHistoryService examPaperHistoryService;
    /**  */
    @Autowired
    private CustomerExamHistoryService customerExamHistoryService;
    /**  */
    @Autowired
    private IQuestionHistoryService questionHistoryService;
    /**  */
    @Autowired
    private CustomerQuestionHistoryService customerQuestionHistoryService;
    /**  */
    @Autowired
    private ExamDtoService examDtoService;

    /**
     * @return 操作消息提醒
     */
    @GetMapping("/test")
    public AjaxResult Test() {
        return AjaxResult.success("Hello World");
    }

    /**
     * @param customerId
     * @param type
     * @return 操作消息提醒
     */
    @GetMapping("/list")
    public AjaxResult ListQuestion(@RequestParam Long customerId,@RequestParam(required = false) String type) throws UnsupportedEncodingException {
        if(type!=null){
            type= URLDecoder.decode(type,"UTF-8");
        }
        List<CustomerQuestionHistory> customerQuestionHistoryList = customerQuestionHistoryService.selectListByCustomerId(customerId,type);
        return AjaxResult.success(customerQuestionHistoryList);
    }

    /**
     * @param customerId
     * @return 操作消息提醒
     */
    @GetMapping("/nullType")
    public AjaxResult NullTypeQuestion(@RequestParam Long customerId) {
        List<CustomerQuestionHistory> customerQuestionHistoryList = customerQuestionHistoryService.selectNullTypeListByCustomerId(customerId);
        return AjaxResult.success(customerQuestionHistoryList);
    }

    /**
     * @return 操作消息提醒
     */
    @GetMapping("/typeList")
    public AjaxResult ListType(){
        List<String> types=questionService.getAllType();
        return AjaxResult.success(types);
    }
    @GetMapping("/typeAndCountList")
    public AjaxResult ListTypeAndCount(){
        List<TypeDto> typeDtoList=questionService.getAllTypeAndCount();
        return AjaxResult.success(typeDtoList);
    }
    /**
     * @param customerQuestionHistoryDto
     * @return 自行封装一个返回类对请求进行统一的值返回
     */
    @PostMapping("/add/questionHistory")
    @ResponseBody
    public Result addQuestionHistory(@RequestBody CustomerQuestionHistoryDto customerQuestionHistoryDto) {
        QuestionHistory questionHistory = customerQuestionHistoryService.handleToQuestionHistory(customerQuestionHistoryDto);
        QuestionHistory questionHistoryDB = new QuestionHistory(null, customerQuestionHistoryDto.getCustomerId(), customerQuestionHistoryDto.getId(), null, null, null);
        List<QuestionHistory> questionHistoryList = questionHistoryService.selectQuestionHistoryList(questionHistoryDB);
        if (questionHistoryList.isEmpty()) {
            int insert = questionHistoryService.insertQuestionHistory(questionHistory);
            if (insert == 1) {
                return Result.success("插入成功");
            } else {
                return Result.error("插入失败");
            }
        } else {
            int update = questionHistoryService.updateQuestionHistory(questionHistory);
            if (update == 1) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        }
    }


    /**
     * @param id
     * @return 操作消息提醒
     */
    @GetMapping("/allquesbyid")
    public AjaxResult ListAllQuestionsByExam(@RequestParam Integer id) {
        List<CustomerExamHistory> list = customerExamHistoryService.selectQuestionsByExamPaperId(id);
        return AjaxResult.success(list);
    }

    /**
     * @param customerId
     * @return 操作消息提醒
     */
    @GetMapping("/maxhistoryques")
    public AjaxResult SelectHistoryQuestion(@RequestParam Integer customerId) {
        Integer max = 1;
        try {
            max = questionHistoryService.selectQuestionHistoryDetail(customerId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return AjaxResult.success(max);
    }

    /**
     * @param examPaperHistory
     * @return 操作消息提醒
     *///更新用户当前题目记录行
    @GetMapping("/changequesnumber")
    public AjaxResult ChangeQuesNumber(@RequestBody ExamPaperHistory examPaperHistory) {
        return toAjax(examPaperHistoryService.update(examPaperHistory));
    }

    /**
     * @return 操作消息提醒
     *///
    @GetMapping("/historyexampaperlist")
    public AjaxResult SelectHistoryExamPaper() {
        return AjaxResult.success();
    }

    /**
     * @param examPaperHistory
     * @return 操作消息提醒
     */
    @PostMapping("/addpaper")
    public AjaxResult insertExamHistory(@RequestBody ExamPaperHistory examPaperHistory) {
        examPaperHistory.setCurrentQuestionNumber(0L);
        return toAjax(examPaperHistoryService.insert(examPaperHistory));
    }

    /**
     * @return
     */
    @GetMapping("/random")
    Result<List> randomQuestion() {
        List<Integer> allQuestionIds = questionService.getAllQuestionIds();

        // 确保至少有50个题目ID
        if (allQuestionIds.size() < 50) {
            return Result.error("不足50道题目");
        }

        // 将List<Integer>转换为Set<Integer>去除重复项，然后转换为List<Long>
        List<Long> randomIds = allQuestionIds.stream().distinct() // 去除重复项
                .skip(allQuestionIds.size() - 50) // 如果题目数量超过50，跳过前面的元素
                .limit(50) // 限制数量为50
                .map(Long::valueOf) // 将Integer转换为Long
                .collect(Collectors.toList()); // 收集为List<Long>

        // 根据随机ID查询题目详情
        List<Question> questions = randomIds.stream().map(id -> questionService.selectQuestionById(id)).collect(Collectors.toList());
        return Result.success(questions);
    }

    /**
     * 获取全部试卷和用户之间的关系
     * @param customerId
     * @return 试卷类
     */
    @GetMapping("/examList")
    public AjaxResult getExamList(@RequestParam(required = false) Long customerId) {
        if(customerId==null){
            customerId= 1L;
        }
         List<ExamDto> examDtoList=examDtoService.selectExamListByCustomerId(customerId);
        return AjaxResult.success(examDtoList);
    }

//    @GetMapping("/getquestionbyid")
//    public AjaxResult getQuestion(@RequestParam Long id,@RequestParam Long customerId){
//        String type ="";
//        List<CustomerQuestionHistory> customerQuestionHistoryList=customerQuestionHistoryService.selectListByCustomerId(customerId,type);
//        if (customerQuestionHistoryList == null || customerQuestionHistoryList.isEmpty()) {
//            return AjaxResult.warn("未找到当前用户所做题目");
//        }
//        Optional<CustomerQuestionHistoryDto> matchingHistory = customerQuestionHistoryList.stream()
//                .map(cq -> {
//                    CustomerQuestionHistoryDto cqd = new CustomerQuestionHistoryDto();
//                    BeanUtils.copyProperties(cq, cqd);
//                    return cqd;
//                })
//                .filter(cqd -> cqd.getId().equals(id))
//                .findFirst();
//
//        if (matchingHistory.isPresent()) {
//            CustomerQuestionHistoryDto cqd = matchingHistory.get();
//            cqd.setCustomerId(customerId);
//            return AjaxResult.success(cqd);
//        } else {
//            return AjaxResult.warn("未找到当前用户所做题目");
//        }
//
//    }
//    public Result<List<>>
}
