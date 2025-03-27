package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.ExamPaperHistory;
import com.ruoyi.api.domain.ExamPaperRecord;
import com.ruoyi.api.mapper.ExamPaperHistoryMapper;
import com.ruoyi.api.service.ExamPaperHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamPaperHistoryServiceImpl implements ExamPaperHistoryService {
  @Autowired
    private ExamPaperHistoryMapper examPaperHistoryMapper;


  @Override
  public int insert(ExamPaperHistory examPaperHistory) {
    return examPaperHistoryMapper.insertExamPaperHistory(examPaperHistory);

  }

  @Override
  public int update(ExamPaperHistory number) {

    return examPaperHistoryMapper.updateExamPaperHistory(number);
  }

    @Override
    public List<ExamPaperHistory> selectQuestionHistoryList(ExamPaperHistory examPaperHistoryDB) {
        return examPaperHistoryMapper.selectExamPaperHistoryList(examPaperHistoryDB);
    }

    @Override
    public int deleteExamHistoryByExamId(Long customerId, Long examId) {
        return examPaperHistoryMapper.deleteExamHistory(customerId,examId);
    }

    @Override
    public int insertExamPaperRecord(ExamPaperRecord examPaperRecord) {
        return examPaperHistoryMapper.insertExamPaperRecord(examPaperRecord);
    }
}
