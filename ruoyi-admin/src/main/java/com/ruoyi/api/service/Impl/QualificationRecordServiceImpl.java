package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.CityDto;
import com.ruoyi.api.domain.QualificationRecord;
import com.ruoyi.api.domain.dto.QualificationDto;
import com.ruoyi.api.mapper.CityMapper;
import com.ruoyi.api.mapper.QualificationRecordMapper;
import com.ruoyi.api.service.CityService;
import com.ruoyi.api.service.QualificationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationRecordServiceImpl implements QualificationRecordService {

    @Autowired
    private QualificationRecordMapper qualificationRecordMapper;


    @Override
    public void insert(String phone, String filePath) {
        QualificationRecord q=new QualificationRecord();
        q.setFileUrl(filePath);
        q.setPhone(phone);
        qualificationRecordMapper.insert(q);
    }
}
