package com.ruoyi.web.controller.swiper.mapper;

import java.util.List;
import com.ruoyi.web.controller.swiper.domain.SwiperPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 轮播图管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-11
 */
@Mapper
public interface SwiperPictureMapper
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
    public List<SwiperPicture> selectSwiperPictureList(SwiperPicture swiperPicture);

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
     * 删除轮播图管理
     *
     * @param id 轮播图管理主键
     * @return 结果
     */
    public int deleteSwiperPictureById(Long id);

    /**
     * 批量删除轮播图管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSwiperPictureByIds(Long[] ids);

    List<SwiperPicture> selectswiperPic(@Param("page") Integer page);
}
