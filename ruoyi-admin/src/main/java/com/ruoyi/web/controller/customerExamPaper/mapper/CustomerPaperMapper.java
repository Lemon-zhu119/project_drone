package com.ruoyi.web.controller.customerExamPaper.mapper;

import java.util.List;
import com.ruoyi.web.controller.customerExamPaper.domain.CustomerPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户考卷记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-19
 */
@Mapper
public interface CustomerPaperMapper 
{
    /**
     * 查询全部用户考卷记录
     *
     * @param id 用户考卷记录主键
     * @return 用户考卷记录
     */
    public CustomerPaper selectCustomerPaperById(Integer id);

    /**
     * 查询用户考卷记录列表
     *
     * @param customerPaper 用户考卷记录
     * @return 用户考卷记录集合
     */
    public List<CustomerPaper> selectCustomerPaperList(CustomerPaper customerPaper);

    /**
     * 新增用户考卷记录
     *
     * @param customerPaper 用户考卷记录
     * @return 结果
     */
    public int insertCustomerPaper(CustomerPaper customerPaper);

    /**
     * 修改用户考卷记录
     *
     * @param customerPaper 用户考卷记录
     * @return 结果
     */
    public int updateCustomerPaper(CustomerPaper customerPaper);

    /**
     * 删除用户考卷记录
     *
     * @param id 用户考卷记录主键
     * @return 结果
     */
    public int deleteCustomerPaperById(Integer id);

    /**
     * 批量删除用户考卷记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerPaperByIds(Integer[] ids);

    /**
     * @param customerId
     * @param examPaperId
     * @return
     */
    List<Double> selectScoreList(@Param("customerId") Long customerId, @Param("examPaperId") Long examPaperId);

    /**
     * @param customerId
     * @return
     */
    List<Long> selectPaperIds(@Param("customerId") Long customerId);


    /**
     * @param examPaperId
     * @param customerId
     * @return 用户考卷记录对象customer_paper
     */
    CustomerPaper selectByExamPaperId(@Param("examPaperId") Long examPaperId, @Param("customerId") Long customerId);
}
