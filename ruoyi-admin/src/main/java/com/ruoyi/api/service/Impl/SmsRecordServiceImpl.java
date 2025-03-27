package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.CityDto;
import com.ruoyi.api.domain.dto.SmsRecord;
import com.ruoyi.api.mapper.CityMapper;
import com.ruoyi.api.mapper.SmsRecordMapper;
import com.ruoyi.api.service.CityService;
import com.ruoyi.api.service.SmsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsRecordServiceImpl implements SmsRecordService {

    @Autowired
    private SmsRecordMapper smsRecordMapper;


    @Override
    public int updateStatus(SmsRecord record) {
     return  smsRecordMapper.updateStatus(record);
    }
}
