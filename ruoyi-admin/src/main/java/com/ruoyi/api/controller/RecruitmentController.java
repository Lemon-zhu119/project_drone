package com.ruoyi.api.controller;


import com.ruoyi.api.domain.Recruitment;
import com.ruoyi.api.mapper.RecruitmentMapper;
import com.ruoyi.api.service.RecruitmentService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;
    @GetMapping("/list")
    public AjaxResult list(){
        List<Recruitment> list=recruitmentService.selectList();
        return AjaxResult.success(list);
    }
}
