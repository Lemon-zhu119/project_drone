package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.CityDto;
import com.ruoyi.api.domain.dto.SmsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SmsRecordMapper {


    int updateStatus(SmsRecord record);
}
