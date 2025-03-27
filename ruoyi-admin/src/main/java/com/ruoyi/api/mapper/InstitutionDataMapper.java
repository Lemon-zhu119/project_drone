package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.InstitutionData;
import com.ruoyi.api.domain.dto.InstitutionDateDto;
import com.ruoyi.api.service.Impl.InstitutionQueryParam;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface InstitutionDataMapper {
    List<InstitutionData> getInstitutionList();

    List<InstitutionData> selectListByType(@Param("subject") String subject);

    List<InstitutionDateDto> selectListByCity(@Param("city") String city);
    List<InstitutionData> selectInsPic();

    InstitutionData selectListById(@Param("id") Integer id);

    List<InstitutionDateDto> selectListByName(@Param("name") String name);

    List<InstitutionDateDto> selectList();

    List<InstitutionDateDto> selectListByProvince(@Param("province") String province);

    List<InstitutionData> selectListByConditions(InstitutionQueryParam queryParam);


    Map<String, Double> getCoordinates(Integer id);
}
