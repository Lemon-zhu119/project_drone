package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.ExamPaperHistory;
import com.ruoyi.api.domain.ExamPaperRecord;
import com.ruoyi.api.domain.dto.ExamDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamPaperHistoryMapper {


    int insertExamPaperHistory(ExamPaperHistory examPaperHistory);


    int updateExamPaperHistory( ExamPaperHistory examPaperHistory);

    List<ExamPaperHistory> selectExamPaperHistoryList(ExamPaperHistory examPaperHistoryDB);

    List<ExamDto> selectExamPaperHistoryListByCustomerId(@Param("customerId") Long customerId);


    int deleteExamHistory(@Param("customerId") Long customerId, @Param("examId") Long examId);
    ExamPaperRecord selectByExamId(Long id);

    int insertExamPaperRecord(ExamPaperRecord examPaperRecord);
}
