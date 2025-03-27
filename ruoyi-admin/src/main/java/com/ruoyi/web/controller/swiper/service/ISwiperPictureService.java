package com.ruoyi.web.controller.swiper.service;

import java.util.List;
import com.ruoyi.web.controller.swiper.domain.SwiperPicture;

/**
 * 轮播图管理Service接口
 *
 * @author ruoyi
 * @date 2024-12-11
 */
public interface ISwiperPictureService
{
    /**
     * 查询轮播图管理
     *
     * @param id 轮播图管理主键
     * @return 轮播图管理
     */
    public SwiperPicture selectSwiperPictureById(Long id);

    /**
     * 查询轮播图管理列表
     *
     * @param swiperPicture 轮播图管理
     * @return 轮播图管理集合
     */


    /**
     * 新增轮播图管理
     *
     * @param swiperPicture 轮播图管理
     * @return 结果
     */
    public int insertSwiperPicture(SwiperPicture swiperPicture);

    /**
     * 修改轮播图管理
     *
     * @param swiperPicture 轮播图管理
     * @return 结果
     */
    public int updateSwiperPicture(SwiperPicture swiperPicture);

    /**
     * 批量删除轮播图管理
     *
     * @param ids 需要删除的轮播图管理主键集合
     * @return 结果
     */
    public int deleteSwiperPictureByIds(Long[] ids);

    /**
     * 删除轮播图管理信息
     *
     * @param id 轮播图管理主键
     * @return 结果
     */
    public int deleteSwiperPictureById(Long id);


    List<SwiperPicture> selectSwiperPictureList(SwiperPicture swiperPicture);

    List<SwiperPicture> selectSwiperPic(Integer page);
}
