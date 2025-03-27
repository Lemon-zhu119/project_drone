package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.dto.ExamDto;
import com.ruoyi.api.mapper.ExamPaperHistoryMapper;
import com.ruoyi.api.service.ExamDtoService;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import com.ruoyi.web.controller.ExamPaper.mapper.ExamPaperMapper;
import com.ruoyi.web.controller.customerExamPaper.domain.CustomerPaper;
import com.ruoyi.web.controller.customerExamPaper.mapper.CustomerPaperMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lemon-zhu119
 * @date 2025-03-09
 */
@Service
public class ExamDtoServiceImpl implements ExamDtoService {

    /**  */
    @Autowired
    private ExamPaperHistoryMapper examPaperHistoryMapper;
    /**  */
    @Autowired
    private ExamPaperMapper examPaperMapper;

    /**  */
    @Autowired
    private CustomerPaperMapper customerPaperMapper;

    /**
     * 返回试卷与用户关系
     *
     * @param customerId --用户id
     * @return 试卷与用户的关系
     */
    @Override
    public List<ExamDto> selectExamListByCustomerId(Long customerId) {
        List<ExamPaper> examPaperList = examPaperMapper.selectExamPaperList(new ExamPaper());
        /*
        List<ExamDto> examPaperHistories =examPaperHistoryMapper.selectExamPaperHistoryListByCustomerId(customerId);
        Set<Long> examPaperHistoriesId=new HashSet<>();

          for(ExamDto examDto:examPaperHistories){
            examPaperHistoriesId.add(examDto.getId());
        }
        */
        List<ExamDto> examDtoList = new ArrayList<>();
        for (ExamPaper examPaper : examPaperList) {
            CustomerPaper customerPaper = customerPaperMapper.selectByExamPaperId(examPaper.getId(),customerId);
            ExamDto examDto = new ExamDto();
            BeanUtils.copyProperties(examPaper, examDto);
            if (customerPaper != null) {
                examDto.setCreateTime(customerPaper.getCreateTime());
                examDto.setInProgress(true);
                examDto.setStatus(customerPaper.getStatus());
                examDtoList.add(examDto);
            } else {
                BeanUtils.copyProperties(examPaper, examDto);
                examDtoList.add(examDto);
            }
        }
        /**
         * 按id排序
         */
        List<ExamDto> sortedExamPaperHistories = examDtoList.stream()
                .sorted(Comparator.comparing(ExamDto::getId))
                .collect(Collectors.toList());
        return sortedExamPaperHistories;
    }
}
