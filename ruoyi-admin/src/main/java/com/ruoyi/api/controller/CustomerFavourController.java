package com.ruoyi.api.controller;

import com.ruoyi.api.common.Result;
import com.ruoyi.api.domain.InstitutionData;
import com.ruoyi.api.domain.dto.CustomerFavourDto;
import com.ruoyi.api.service.CustomerDtoServer;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import com.ruoyi.web.controller.customerfavor.domain.CustomerFavor;
import com.ruoyi.web.controller.customerfavor.service.ICustomerFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/favour")
public class CustomerFavourController {
    @Autowired
    private CustomerDtoServer customerDtoServer;
    @Autowired
    private ICustomerFavorService customerFavorService;

    @GetMapping("/questionlist")
    public AjaxResult getQuestionFavourList(@RequestParam(required = false) Integer customerId
                                                ) {
        if (customerId == null) {
            customerId = 1;
        }
        List<CustomerFavourDto> list=customerDtoServer.selectCustomerQuestionFavourServer(customerId);
        List<CustomerFavourDto> sortList=list.stream().sorted(Comparator.comparing(CustomerFavourDto::getQuestionId)).collect(Collectors.toList());

        return AjaxResult.success(sortList);
    }
    @GetMapping("/exampaperlist")
    public AjaxResult getExamPaperFavourList(@RequestParam(required = false) Integer customerId
                                                 ) {
        if (customerId == null) {
            customerId = 1;
        }
        return AjaxResult.success(customerDtoServer.selectCustomerExamPaperFavourServer(customerId));
    }
    @GetMapping("/institutionlist")
    public Result<List<InstitutionData>> getInstitutionFavourList(@RequestParam(required = false) Integer customerId
                                                       ) {
        if (customerId == null) {
            customerId = 1;
        }
        return Result.success(customerDtoServer.selectCustomerInstitutionFavourServer(customerId));
    }
    @GetMapping("/add/favorite")
    public AjaxResult addFavorite(@RequestParam(required = false) Integer customerId,
                              @RequestParam(required = false) Integer questionId,
                              @RequestParam(required = false) Integer examPaperId,
                              @RequestParam(required = false) Integer institutionId){
        if(customerId==null){
            customerId=1;
        }
        CustomerFavor customerFavor=new CustomerFavor(null,questionId,examPaperId,institutionId,customerId);
        List<CustomerFavor> customerFavorList=customerFavorService.selectCustomerFavorList(customerFavor);
        if(!customerFavorList.isEmpty()){
            return AjaxResult.error("已收藏，逻辑错误");
        }else {
            int add=customerFavorService.insertCustomerFavor(customerFavor);
            if(add==1){
                return AjaxResult.success("添加收藏成功");
            }else {
                return AjaxResult.error("添加失败");
            }
        }
    }
    @GetMapping("/del/favorite")
    public AjaxResult delFavorite(@RequestParam(required = false) Integer customerId,
                              @RequestParam(required = false) Integer questionId,
                              @RequestParam(required = false) Integer examPaperId,
                              @RequestParam(required = false) Integer institutionId){
        if(customerId==null){
            customerId=1;
        }
        CustomerFavor customerFavor=new CustomerFavor(null,questionId,examPaperId,institutionId,customerId);
        List<CustomerFavor> customerFavorList=customerFavorService.selectCustomerFavorList(customerFavor);
        if(customerFavorList.isEmpty()){
            return AjaxResult.error("已取消收藏，逻辑错误");
        }else {
            int del=customerFavorService.deleteCustomerFavorById(customerFavorList.get(0).getId());
            if(del==1){
                return AjaxResult.success("取消收藏成功");
            }else {
                return AjaxResult.error("取消收藏失败");
            }
        }
    }
}
