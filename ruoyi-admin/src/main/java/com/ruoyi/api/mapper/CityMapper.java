package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.CityDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CityMapper {


    List<CityDto> selectList(@Param("provinceId") Integer provinceId);
}
