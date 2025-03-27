package com.ruoyi.web.controller.QuestionHistory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.QuestionHistory.mapper.QuestionHistoryMapper;
import com.ruoyi.web.controller.QuestionHistory.domain.QuestionHistory;
import com.ruoyi.web.controller.QuestionHistory.service.IQuestionHistoryService;

/**
 * 用户顺序练习记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-14
 */
@Service
public class QuestionHistoryServiceImpl implements IQuestionHistoryService
{
    @Autowired
    private QuestionHistoryMapper questionHistoryMapper;

    /**
     * 查询用户顺序练习记录
     *
     * @param id 用户顺序练习记录主键
     * @return 用户顺序练习记录
     */
    @Override
    public QuestionHistory selectQuestionHistoryById(Long id)
    {
        return questionHistoryMapper.selectQuestionHistoryById(id);
    }

    /**
     * 查询用户顺序练习记录列表
     *
     * @param questionHistory 用户顺序练习记录
     * @return 用户顺序练习记录
     */
    @Override
    public List<QuestionHistory> selectQuestionHistoryList(QuestionHistory questionHistory)
    {
        return questionHistoryMapper.selectQuestionHistoryList(questionHistory);
    }

    /**
     * 新增用户顺序练习记录
     *
     * @param questionHistory 用户顺序练习记录
     * @return 结果
     */
    @Override
    public int insertQuestionHistory(QuestionHistory questionHistory)
    {
        questionHistory.setCreateTime(DateUtils.getNowDate());
        return questionHistoryMapper.insertQuestionHistory(questionHistory);
    }

    /**
     * 修改用户顺序练习记录
     *
     * @param questionHistory 用户顺序练习记录
     * @return 结果
     */
    @Override
    public int updateQuestionHistory(QuestionHistory questionHistory)
    {
        questionHistory.setUpdateTime(DateUtils.getNowDate());
        return questionHistoryMapper.updateQuestionHistory(questionHistory);
    }

    /**
     * 批量删除用户顺序练习记录
     *
     * @param ids 需要删除的用户顺序练习记录主键
     * @return 结果
     */
    @Override
    public int deleteQuestionHistoryByIds(Long[] ids)
    {
        return questionHistoryMapper.deleteQuestionHistoryByIds(ids);
    }

    /**
     * 删除用户顺序练习记录信息
     *
     * @param id 用户顺序练习记录主键
     * @return 结果
     */
    @Override
    public int deleteQuestionHistoryById(Long id)
    {
        return questionHistoryMapper.deleteQuestionHistoryById(id);
    }

    @Override
    public Integer selectQuestionHistoryDetail(Integer customerId) {
        Integer history=questionHistoryMapper.selectQuestionHistoryDetail(customerId);
        if(history==0){
            history=1;
        }
        return history;
    }
}
