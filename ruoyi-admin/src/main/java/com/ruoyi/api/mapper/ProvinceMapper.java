package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProvinceMapper {


    List<Province> selectProvinceList();
    String selectProvinceNameById(@Param("provinceId") Integer provinceId);
}
