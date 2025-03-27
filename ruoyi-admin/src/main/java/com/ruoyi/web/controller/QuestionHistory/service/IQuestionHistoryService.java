package com.ruoyi.web.controller.QuestionHistory.service;

import java.util.List;
import com.ruoyi.web.controller.QuestionHistory.domain.QuestionHistory;

/**
 * 用户顺序练习记录Service接口
 *
 * @author ruoyi
 * @date 2024-12-14
 */
public interface IQuestionHistoryService
{
    /**
     * 查询用户顺序练习记录
     *
     * @param id 用户顺序练习记录主键
     * @return 用户顺序练习记录
     */
    public QuestionHistory selectQuestionHistoryById(Long id);

    /**
     * 查询用户顺序练习记录列表
     *
     * @param questionHistory 用户顺序练习记录
     * @return 用户顺序练习记录集合
     */
    public List<QuestionHistory> selectQuestionHistoryList(QuestionHistory questionHistory);

    /**
     * 新增用户顺序练习记录
     *
     * @param questionHistory 用户顺序练习记录
     * @return 结果
     */
    public int insertQuestionHistory(QuestionHistory questionHistory);

    /**
     * 修改用户顺序练习记录
     *
     * @param questionHistory 用户顺序练习记录
     * @return 结果
     */
    public int updateQuestionHistory(QuestionHistory questionHistory);

    /**
     * 批量删除用户顺序练习记录
     *
     * @param ids 需要删除的用户顺序练习记录主键集合
     * @return 结果
     */
    public int deleteQuestionHistoryByIds(Long[] ids);

    /**
     * 删除用户顺序练习记录信息
     *
     * @param id 用户顺序练习记录主键
     * @return 结果
     */
    public int deleteQuestionHistoryById(Long id);

    Integer selectQuestionHistoryDetail(Integer customerId);
}
