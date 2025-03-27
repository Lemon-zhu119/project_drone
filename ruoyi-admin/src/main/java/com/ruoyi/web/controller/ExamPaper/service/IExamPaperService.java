package com.ruoyi.web.controller.ExamPaper.service;

import java.util.List;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaperType;
import com.ruoyi.web.controller.question.domain.Question;

/**
 * 考卷管理Service接口
 * 
 * @author ruoyi
 * @date 2024-12-14
 */
public interface IExamPaperService 
{
    /**
     * 查询考卷管理
     * 
     * @param id 考卷管理主键
     * @return 考卷管理
     */
    public ExamPaper selectExamPaperById(Long id);

    /**
     * 查询考卷管理列表
     * 
     * @param examPaper 考卷管理
     * @return 考卷管理集合
     */
    public List<ExamPaper> selectExamPaperList(ExamPaper examPaper);

    /**
     * 新增考卷管理
     * 
     * @param examPaper 考卷管理
     * @return 结果
     */
    public int insertExamPaper(ExamPaper examPaper);

    /**
     * 修改考卷管理
     * 
     * @param examPaper 考卷管理
     * @return 结果
     */
    public int updateExamPaper(ExamPaper examPaper);

    /**
     * 批量删除考卷管理
     * 
     * @param ids 需要删除的考卷管理主键集合
     * @return 结果
     */
    public int deleteExamPaperByIds(Long[] ids);

    /**
     * 删除考卷管理信息
     * 
     * @param id 考卷管理主键
     * @return 结果
     */
    public int deleteExamPaperById(Long id);

    boolean insertRandomGenerate(ExamPaperType examPaperType);

    List<Question> selectExamPaperDetailList(Question question, Long examPaperId);

    List<Question> selectExamPaperRemainDetailList(Question question, Long examPaperId);
}
