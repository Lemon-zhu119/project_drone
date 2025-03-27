package com.ruoyi.web.controller.question.mapper;

import java.util.List;

import com.ruoyi.web.controller.ExamPaper.domain.TypeDto;
import com.ruoyi.web.controller.question.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 题目管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-11
 */
@Mapper
public interface QuestionMapper
{
    /**
     * 查询题目管理
     *
     * @param id 题目管理主键
     * @return 题目管理
     */
    public Question selectQuestionById(Long id);

    /**
     * 查询题目管理列表
     *
     * @param question 题目管理
     * @return 题目管理集合
     */
    public List<Question> selectQuestionList(Question question);

    /**
     * 新增题目管理
     *
     * @param question 题目管理
     * @return 结果
     */
    public int insertQuestion(Question question);

    /**
     * 修改题目管理
     *
     * @param question 题目管理
     * @return 结果
     */
    public int updateQuestion(Question question);
    int updateQuestionByContent(Question question);

    /**
     * 删除题目管理
     *
     * @param id 题目管理主键
     * @return 结果
     */
    public int deleteQuestionById(Long id);

    /**
     * 批量删除题目管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionByIds(Long[] ids);


    List<Question> selectTop10(Question question, @Param("pageNum") Integer pageNum, @Param("pageLimit") Integer pageLimit);


    List<Integer> getAllQuestionIds();

    List<String> getAllType();

    int saveOrUpdateQuestion(Question question);

    List<TypeDto> getAllTypeAndCount();
}
