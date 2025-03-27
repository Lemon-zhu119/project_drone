package com.ruoyi.web.controller.customerfavor.service;

import java.util.List;
import com.ruoyi.web.controller.customerfavor.domain.CustomerFavor;

/**
 * 用户收藏Service接口
 * 
 * @author ruoyi
 * @date 2024-12-19
 */
public interface ICustomerFavorService 
{
    /**
     * 查询用户收藏
     * 
     * @param id 用户收藏主键
     * @return 用户收藏
     */
    public CustomerFavor selectCustomerFavorById(Integer id);

    /**
     * 查询用户收藏列表
     * 
     * @param customerFavor 用户收藏
     * @return 用户收藏集合
     */
    public List<CustomerFavor> selectCustomerFavorList(CustomerFavor customerFavor);

    /**
     * 新增用户收藏
     * 
     * @param customerFavor 用户收藏
     * @return 结果
     */
    public int insertCustomerFavor(CustomerFavor customerFavor);

    /**
     * 修改用户收藏
     * 
     * @param customerFavor 用户收藏
     * @return 结果
     */
    public int updateCustomerFavor(CustomerFavor customerFavor);

    /**
     * 批量删除用户收藏
     * 
     * @param ids 需要删除的用户收藏主键集合
     * @return 结果
     */
    public int deleteCustomerFavorByIds(Integer[] ids);

    /**
     * 删除用户收藏信息
     * 
     * @param id 用户收藏主键
     * @return 结果
     */
    public int deleteCustomerFavorById(Integer id);
}
