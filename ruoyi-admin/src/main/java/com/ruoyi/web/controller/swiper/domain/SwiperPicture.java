package com.ruoyi.web.controller.swiper.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图管理对象 swiper_picture
 *
 * @author ruoyi
 * @date 2024-12-11
 */
public class SwiperPicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 轮播图名称 */
    @Excel(name = "轮播图名称")
    private String name;

    /** 轮播图地址 */
    @Excel(name = "轮播图地址")
    private String url;
    @Excel(name = "轮播图页码")
    private String page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public SwiperPicture(Long id, String name, String url, String page) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.page = page;
    }

    public SwiperPicture() {
    }

    @Override
    public String toString() {
        return "SwiperPicture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
