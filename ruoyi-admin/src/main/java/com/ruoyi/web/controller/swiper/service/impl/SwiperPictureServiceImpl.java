package com.ruoyi.web.controller.swiper.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.swiper.mapper.SwiperPictureMapper;
import com.ruoyi.web.controller.swiper.domain.SwiperPicture;
import com.ruoyi.web.controller.swiper.service.ISwiperPictureService;

/**
 * 轮播图管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-12-11
 */
@Service
public class SwiperPictureServiceImpl implements ISwiperPictureService
{
    @Autowired
    private SwiperPictureMapper swiperPictureMapper;

    /**
     * 查询轮播图管理
     *
     * @param id 轮播图管理主键
     * @return 轮播图管理
     */
    @Override
    public SwiperPicture selectSwiperPictureById(Long id)
    {
        return swiperPictureMapper.selectSwiperPictureById(id);
    }

    /**
     * 查询轮播图管理列表
     *
     * @param swiperPicture 轮播图管理
     * @return 轮播图管理
     */


    /**
     * 新增轮播图管理
     *
     * @param swiperPicture 轮播图管理
     * @return 结果
     */
    @Override
    public int insertSwiperPicture(SwiperPicture swiperPicture)
    {
        return swiperPictureMapper.insertSwiperPicture(swiperPicture);
    }

    /**
     * 修改轮播图管理
     *
     * @param swiperPicture 轮播图管理
     * @return 结果
     */
    @Override
    public int updateSwiperPicture(SwiperPicture swiperPicture)
    {
        return swiperPictureMapper.updateSwiperPicture(swiperPicture);
    }

    /**
     * 批量删除轮播图管理
     *
     * @param ids 需要删除的轮播图管理主键
     * @return 结果
     */
    @Override
    public int deleteSwiperPictureByIds(Long[] ids)
    {
        return swiperPictureMapper.deleteSwiperPictureByIds(ids);
    }

    /**
     * 删除轮播图管理信息
     *
     * @param id 轮播图管理主键
     * @return 结果
     */
    @Override
    public int deleteSwiperPictureById(Long id)
    {
        return swiperPictureMapper.deleteSwiperPictureById(id);
    }

    @Override
    public List<SwiperPicture> selectSwiperPictureList(SwiperPicture swiperPicture) {
        return swiperPictureMapper.selectSwiperPictureList(swiperPicture);
    }

    @Override
    public List<SwiperPicture> selectSwiperPic(Integer page) {
        return swiperPictureMapper.selectswiperPic(page);
    }
}
