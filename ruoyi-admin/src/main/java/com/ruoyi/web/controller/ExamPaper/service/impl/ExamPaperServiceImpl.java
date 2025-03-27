package com.ruoyi.web.controller.ExamPaper.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaperType;
import com.ruoyi.web.controller.ExamPaper.domain.TypeDto;
import com.ruoyi.web.controller.ExamPaper.mapper.ExamPaperMapper;
import com.ruoyi.web.controller.ExamPaper.service.IExamPaperService;
import com.ruoyi.web.controller.ExamPaperDetail.domain.ExamPaperDetail;
import com.ruoyi.web.controller.ExamPaperDetail.mapper.ExamPaperDetailMapper;
import com.ruoyi.web.controller.question.domain.Question;
import com.ruoyi.web.controller.question.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 考卷管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-14
 */
@Service
public class ExamPaperServiceImpl implements IExamPaperService 
{
    @Autowired
    private ExamPaperMapper examPaperMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ExamPaperDetailMapper examPaperDetailMapper;

    /**
     * 查询考卷管理
     * 
     * @param id 考卷管理主键
     * @return 考卷管理
     */
    @Override
    public ExamPaper selectExamPaperById(Long id)
    {
        return examPaperMapper.selectExamPaperById(id);
    }

    /**
     * 查询考卷管理列表
     * 
     * @param examPaper 考卷管理
     * @return 考卷管理
     */
    @Override
    public List<ExamPaper> selectExamPaperList(ExamPaper examPaper)
    {
        List<ExamPaper> examPaperList=examPaperMapper.selectExamPaperList(examPaper);
        hasDetail(examPaperList);
        return examPaperList;
    }

    private void hasDetail(List<ExamPaper> examPaperList) {
        for(ExamPaper examPaper:examPaperList){
            examPaper.setHaveDetail(!examPaperDetailMapper.selectExamPaperDetailList(new ExamPaperDetail(null,null, examPaper.getId(), null,null,null)).isEmpty());
        }
    }

    /**
     * 新增考卷管理
     * 
     * @param examPaper 考卷管理
     * @return 结果
     */
    @Override
    public int insertExamPaper(ExamPaper examPaper)
    {
        return examPaperMapper.insertExamPaper(examPaper);
    }

    /**
     * 修改考卷管理
     * 
     * @param examPaper 考卷管理
     * @return 结果
     */
    @Override
    public int updateExamPaper(ExamPaper examPaper)
    {
        examPaper.setUpdateTime(DateUtils.getNowDate());
        return examPaperMapper.updateExamPaper(examPaper);
    }

    /**
     * 批量删除考卷管理
     * 
     * @param ids 需要删除的考卷管理主键
     * @return 结果
     */
    @Override
    public int deleteExamPaperByIds(Long[] ids)
    {
        return examPaperMapper.deleteExamPaperByIds(ids);
    }

    /**
     * 删除考卷管理信息
     * 
     * @param id 考卷管理主键
     * @return 结果
     */
    @Override
    public int deleteExamPaperById(Long id)
    {
        return examPaperMapper.deleteExamPaperById(id);
    }

    /**
     * @param examPaperType
     * @return
     */
    @Override
    public boolean insertRandomGenerate(ExamPaperType examPaperType) {
        Long examPaperId= examPaperType.getExamPaperId();
        List<Long> allQuestionId=getAllQuestionId(examPaperType.getTypeConfigs());
        return insertExamPaperDetail(examPaperId,allQuestionId);
    }

    /**
     * @param question
     * @param examPaperId
     * @return
     */
    @Override
    public List<Question> selectExamPaperDetailList(Question question, Long examPaperId) {
        return examPaperMapper.selectExamPaperDetailList(question,examPaperId);
    }

    /**
     * @param question
     * @param examPaperId
     * @return
     */
    @Override
    public List<Question> selectExamPaperRemainDetailList(Question question, Long examPaperId) {
        return examPaperMapper.selectExamPaperRemainDetailList(question,examPaperId);
    }

    private boolean insertExamPaperDetail(Long examPaperId, List<Long> allQuestionId) {
        for(Long questionId:allQuestionId){
            Question question=questionMapper.selectQuestionById(questionId);
            if(examPaperDetailMapper.insertExamPaperDetail(new ExamPaperDetail(null,questionId,examPaperId,null,null,question.getScore()))<1){
                return false;
            }
        }
        return true;
    }

    /**
     * 获取全部满足的试卷id
     * @param typeDtoList
     * @return
     */
    private List<Long> getAllQuestionId(List<TypeDto> typeDtoList) {
        List<Long> questionIds=new ArrayList<>();
        for(TypeDto typeDto:typeDtoList){
            Long count=typeDto.getCount();
            List<Question> questionList=questionMapper.selectQuestionList(new Question(typeDto.getType()));
            questionIds.addAll(selectRandomQuestionIds(questionList,count));
        }
        return questionIds;
    }

    private List<Long> selectRandomQuestionIds(List<Question> questionList, Long count) {
        Collections.shuffle(questionList);

        // 确保 count 不超过列表的大小
        int actualCount = (int) Math.min(count, questionList.size());

        // 选取前 count 个元素并返回它们的 ID
        return questionList.subList(0, actualCount).stream()
                .map(Question::getId)
                .collect(Collectors.toList());
    }
}
