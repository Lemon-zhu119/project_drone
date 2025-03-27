package com.ruoyi.web.controller.question.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.web.controller.ExamPaper.domain.TypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.question.mapper.QuestionMapper;
import com.ruoyi.web.controller.question.domain.Question;
import com.ruoyi.web.controller.question.service.IQuestionService;
import com.ruoyi.web.controller.question.service.*;

/**
 * 题目管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-11
 */
@Service
public class QuestionServiceImpl implements IQuestionService
{
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 查询题目管理
     *
     * @param id 题目管理主键
     * @return 题目管理
     */
    @Override
    public Question selectQuestionById(Long id)
    {
        return questionMapper.selectQuestionById(id);
    }

    /**
     * 查询题目管理列表
     *
     * @param question 题目管理
     * @return 题目管理
     */
    @Override
    public List<Question> selectQuestionList(Question question)
    {
        return questionMapper.selectQuestionList(question);
    }

    /**
     * 新增题目管理
     *
     * @param question 题目管理
     * @return 结果
     */
    @Override
    public int insertQuestion(Question question)
    {
        return questionMapper.insertQuestion(question);
    }

    /**
     * 修改题目管理
     *
     * @param question 题目管理
     * @return 结果
     */
    @Override
    public int updateQuestion(Question question)
    {
        return questionMapper.updateQuestion(question);
    }

    /**
     * 批量删除题目管理
     *
     * @param ids 需要删除的题目管理主键
     * @return 结果
     */
    @Override
    public int deleteQuestionByIds(Long[] ids)
    {
        return questionMapper.deleteQuestionByIds(ids);
    }

    /**
     * 删除题目管理信息
     *
     * @param id 题目管理主键
     * @return 结果
     */
    @Override
    public int deleteQuestionById(Long id)
    {
        return questionMapper.deleteQuestionById(id);
    }

    @Override
    public List<Question> selectTop10(Question question, Integer pageNum, Integer pageLimit) {
        return questionMapper.selectTop10(question,pageNum,pageLimit);
    }

    @Override
    public List<Integer> getAllQuestionIds() {
        return questionMapper.getAllQuestionIds();
    }

    @Override
    public List<String> getAllType() {
        return questionMapper.getAllType();
    }
    @Override
    public void saveOrUpdate(Question question) {
        try {
            // 使用新的基于content更新的方法
            int updateRows = questionMapper.updateQuestionByContent(question);
            if (updateRows == 0) {
                // 如果更新影响行数为0（不存在），则尝试插入
                int insertRows = questionMapper.saveOrUpdateQuestion(question);
                if (insertRows == 0) {
                    throw new ServiceException("题目：" + question.getContent() + " 保存失败");
                }
            }
        } catch (Exception e) {
            throw new ServiceException("题目：" + question.getContent() + " 操作失败，原因：" + e.getMessage());
        }
    }

    @Override
    public List<TypeDto> getAllTypeAndCount() {
        return questionMapper.getAllTypeAndCount();
    }
}
