package com.ruoyi.web.controller.customerExamPaper.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.customerExamPaper.mapper.CustomerPaperMapper;
import com.ruoyi.web.controller.customerExamPaper.domain.CustomerPaper;
import com.ruoyi.web.controller.customerExamPaper.service.ICustomerPaperService;

/**
 * 用户考卷记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-19
 */
@Service
public class CustomerPaperServiceImpl implements ICustomerPaperService 
{
    @Autowired
    private CustomerPaperMapper customerPaperMapper;

    /**
     * 查询用户考卷记录
     * 
     * @param id 用户考卷记录主键
     * @return 用户考卷记录
     */
    @Override
    public CustomerPaper selectCustomerPaperById(Integer id)
    {
        return customerPaperMapper.selectCustomerPaperById(id);
    }

    /**
     * 查询用户考卷记录列表
     * 
     * @param customerPaper 用户考卷记录
     * @return 用户考卷记录
     */
    @Override
    public List<CustomerPaper> selectCustomerPaperList(CustomerPaper customerPaper)
    {
        return customerPaperMapper.selectCustomerPaperList(customerPaper);
    }

    /**
     * 新增用户考卷记录
     * 
     * @param customerPaper 用户考卷记录
     * @return 结果
     */
    @Override
    public int insertCustomerPaper(CustomerPaper customerPaper)
    {
        customerPaper.setCreateTime(DateUtils.getNowDate());
        return customerPaperMapper.insertCustomerPaper(customerPaper);
    }

    /**
     * 修改用户考卷记录
     * 
     * @param customerPaper 用户考卷记录
     * @return 结果
     */
    @Override
    public int updateCustomerPaper(CustomerPaper customerPaper)
    {
        customerPaper.setUpdateTime(DateUtils.getNowDate());
        return customerPaperMapper.updateCustomerPaper(customerPaper);
    }

    /**
     * 批量删除用户考卷记录
     * 
     * @param ids 需要删除的用户考卷记录主键
     * @return 结果
     */
    @Override
    public int deleteCustomerPaperByIds(Integer[] ids)
    {
        return customerPaperMapper.deleteCustomerPaperByIds(ids);
    }

    /**
     * 删除用户考卷记录信息
     * 
     * @param id 用户考卷记录主键
     * @return 结果
     */
    @Override
    public int deleteCustomerPaperById(Integer id)
    {
        return customerPaperMapper.deleteCustomerPaperById(id);
    }
}
