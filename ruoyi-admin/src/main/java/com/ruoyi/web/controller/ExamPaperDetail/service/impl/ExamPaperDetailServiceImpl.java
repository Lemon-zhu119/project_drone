package com.ruoyi.web.controller.ExamPaperDetail.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.ExamPaperDetail.domain.ExamPaperDetail;
import com.ruoyi.web.controller.ExamPaperDetail.mapper.ExamPaperDetailMapper;
import com.ruoyi.web.controller.ExamPaperDetail.service.IExamPaperDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考卷管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-14
 */
@Service
public class ExamPaperDetailServiceImpl implements IExamPaperDetailService 
{
    @Autowired
    private ExamPaperDetailMapper examPaperDetailMapper;

    /**
     * 查询考卷管理
     * 
     * @param id 考卷管理主键
     * @return 考卷管理
     */
    @Override
    public ExamPaperDetail selectExamPaperDetailById(Long id)
    {
        return examPaperDetailMapper.selectExamPaperDetailById(id);
    }

    /**
     * 查询考卷管理列表
     * 
     * @param examPaperDetail 考卷管理
     * @return 考卷管理
     */
    @Override
    public List<ExamPaperDetail> selectExamPaperDetailList(ExamPaperDetail examPaperDetail)
    {
        return examPaperDetailMapper.selectExamPaperDetailList(examPaperDetail);
    }

    /**
     * 新增考卷管理
     * 
     * @param examPaperDetail 考卷管理
     * @return 结果
     */
    @Override
    public int insertExamPaperDetail(ExamPaperDetail examPaperDetail)
    {
        return examPaperDetailMapper.insertExamPaperDetail(examPaperDetail);
    }

    /**
     * 修改考卷管理
     * 
     * @param examPaperDetail 考卷管理
     * @return 结果
     */
    @Override
    public int updateExamPaperDetail(ExamPaperDetail examPaperDetail)
    {
        examPaperDetail.setUpdateTime(DateUtils.getNowDate());
        return examPaperDetailMapper.updateExamPaperDetail(examPaperDetail);
    }

    /**
     * 批量删除考卷管理
     * 
     * @param ids 需要删除的考卷管理主键
     * @return 结果
     */
    @Override
    public int deleteExamPaperDetailByIds(Long[] ids)
    {
        return examPaperDetailMapper.deleteExamPaperDetailByIds(ids);
    }

    /**
     * 删除考卷管理信息
     * 
     * @param id 考卷管理主键
     * @return 结果
     */
    @Override
    public int deleteExamPaperDetailById(Long id)
    {
        return examPaperDetailMapper.deleteExamPaperDetailById(id);
    }

    @Override
    public int deleteExamPaperDetail(ExamPaperDetail examPaperDetail) {
        return examPaperDetailMapper.deleteExamPaperDetail(examPaperDetail);
    }
}
