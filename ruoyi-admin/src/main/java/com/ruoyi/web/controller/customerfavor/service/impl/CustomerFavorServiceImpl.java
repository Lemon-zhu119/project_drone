package com.ruoyi.web.controller.customerfavor.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.customerfavor.mapper.CustomerFavorMapper;
import com.ruoyi.web.controller.customerfavor.domain.CustomerFavor;
import com.ruoyi.web.controller.customerfavor.service.ICustomerFavorService;

/**
 * 用户收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-12-19
 */
@Service
public class CustomerFavorServiceImpl implements ICustomerFavorService 
{
    @Autowired
    private CustomerFavorMapper customerFavorMapper;

    /**
     * 查询用户收藏
     * 
     * @param id 用户收藏主键
     * @return 用户收藏
     */
    @Override
    public CustomerFavor selectCustomerFavorById(Integer id)
    {
        return customerFavorMapper.selectCustomerFavorById(id);
    }

    /**
     * 查询用户收藏列表
     * 
     * @param customerFavor 用户收藏
     * @return 用户收藏
     */
    @Override
    public List<CustomerFavor> selectCustomerFavorList(CustomerFavor customerFavor)
    {
        return customerFavorMapper.selectCustomerFavorList(customerFavor);
    }

    /**
     * 新增用户收藏
     * 
     * @param customerFavor 用户收藏
     * @return 结果
     */
    @Override
    public int insertCustomerFavor(CustomerFavor customerFavor)
    {
        customerFavor.setFavouriteTime(new Date());
        return customerFavorMapper.insertCustomerFavor(customerFavor);
    }

    /**
     * 修改用户收藏
     * 
     * @param customerFavor 用户收藏
     * @return 结果
     */
    @Override
    public int updateCustomerFavor(CustomerFavor customerFavor)
    {
        return customerFavorMapper.updateCustomerFavor(customerFavor);
    }

    /**
     * 批量删除用户收藏
     * 
     * @param ids 需要删除的用户收藏主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFavorByIds(Integer[] ids)
    {
        return customerFavorMapper.deleteCustomerFavorByIds(ids);
    }

    /**
     * 删除用户收藏信息
     * 
     * @param id 用户收藏主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFavorById(Integer id)
    {
        return customerFavorMapper.deleteCustomerFavorById(id);
    }
}
