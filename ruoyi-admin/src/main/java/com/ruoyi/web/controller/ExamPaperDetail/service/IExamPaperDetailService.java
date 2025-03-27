package com.ruoyi.web.controller.ExamPaperDetail.service;

import com.ruoyi.web.controller.ExamPaperDetail.domain.ExamPaperDetail;

import java.util.List;

/**
 * 考卷管理Service接口
 * 
 * @author ruoyi
 * @date 2024-12-14
 */
public interface IExamPaperDetailService 
{
    /**
     * 查询考卷管理
     * 
     * @param id 考卷管理主键
     * @return 考卷管理
     */
    public ExamPaperDetail selectExamPaperDetailById(Long id);

    /**
     * 查询考卷管理列表
     * 
     * @param examPaperDetail 考卷管理
     * @return 考卷管理集合
     */
    public List<ExamPaperDetail> selectExamPaperDetailList(ExamPaperDetail examPaperDetail);

    /**
     * 新增考卷管理
     * 
     * @param examPaperDetail 考卷管理
     * @return 结果
     */
    public int insertExamPaperDetail(ExamPaperDetail examPaperDetail);

    /**
     * 修改考卷管理
     * 
     * @param examPaperDetail 考卷管理
     * @return 结果
     */
    public int updateExamPaperDetail(ExamPaperDetail examPaperDetail);

    /**
     * 批量删除考卷管理
     * 
     * @param ids 需要删除的考卷管理主键集合
     * @return 结果
     */
    public int deleteExamPaperDetailByIds(Long[] ids);

    /**
     * 删除考卷管理信息
     * 
     * @param id 考卷管理主键
     * @return 结果
     */
    public int deleteExamPaperDetailById(Long id);

    int deleteExamPaperDetail(ExamPaperDetail examPaperDetail);
}
