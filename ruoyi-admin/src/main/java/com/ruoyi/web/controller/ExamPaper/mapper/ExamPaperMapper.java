package com.ruoyi.web.controller.ExamPaper.mapper;

import java.util.List;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import com.ruoyi.web.controller.question.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 考卷管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-14
 */
@Mapper
public interface ExamPaperMapper
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
     * 删除考卷管理
     *
     * @param id 考卷管理主键
     * @return 结果
     */
    public int deleteExamPaperById(Long id);

    /**
     * 批量删除考卷管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamPaperByIds(Long[] ids);

    /**
     * 通过id查找试卷的时长
     * @param id
     * @return
     */
    ExamPaper selectdurationByid(Long id);

    /**
     * 通过试卷id 和 题目的查询条件 查询该试卷的题目
     * @param question
     * @param examPaperId
     * @return 题目类型的数组
     */
    List<Question> selectExamPaperDetailList(@Param("question") Question question, @Param("examPaperId") Long examPaperId);

    /**
     * @param question
     * @param examPaperId
     * @return
     */
    List<Question> selectExamPaperRemainDetailList(@Param("question") Question question, @Param("examPaperId") Long examPaperId);
}
