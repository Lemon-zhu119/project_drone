package com.ruoyi.api.service;

import com.ruoyi.api.domain.ExamPaperHistory;
import com.ruoyi.api.domain.ExamPaperRecord;

import java.util.List;

public interface ExamPaperHistoryService {


    int insert(ExamPaperHistory examPaperHistory);

    int update(ExamPaperHistory number);

    List<ExamPaperHistory> selectQuestionHistoryList(ExamPaperHistory examPaperHistoryDB);

    int deleteExamHistoryByExamId(Long customerId, Long examId);

    int insertExamPaperRecord(ExamPaperRecord examPaperRecord);
}
