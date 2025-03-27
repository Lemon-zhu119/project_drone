package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.Options;
import com.ruoyi.api.domain.Province;
import com.ruoyi.api.mapper.CustomerExamHistoryMapper;
import com.ruoyi.api.mapper.ProvinceMapper;
import com.ruoyi.api.service.CustomerExamHistoryService;
import com.ruoyi.api.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<Province> getProvinceList() {
        return provinceMapper.selectProvinceList();
    }

    @Override
    public String selectProvinceNameById(Integer provinceId) {
        return provinceMapper.selectProvinceNameById(provinceId);
    }
}
