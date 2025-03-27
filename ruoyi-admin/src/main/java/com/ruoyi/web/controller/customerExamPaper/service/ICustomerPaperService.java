package com.ruoyi.web.controller.customerExamPaper.service;

import java.util.List;
import com.ruoyi.web.controller.customerExamPaper.domain.CustomerPaper;

/**
 * 用户考卷记录Service接口
 * 
 * @author ruoyi
 * @date 2024-12-19
 */
public interface ICustomerPaperService 
{
    /**
     * 查询用户考卷记录
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
     * 批量删除用户考卷记录
     * 
     * @param ids 需要删除的用户考卷记录主键集合
     * @return 结果
     */
    public int deleteCustomerPaperByIds(Integer[] ids);

    /**
     * 删除用户考卷记录信息
     * 
     * @param id 用户考卷记录主键
     * @return 结果
     */
    public int deleteCustomerPaperById(Integer id);
}
