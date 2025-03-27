package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.CityDto;
import com.ruoyi.api.mapper.CityMapper;
import com.ruoyi.api.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;


    @Override
    public List<CityDto> selectList(Integer provinceId) {
        return cityMapper.selectList(provinceId);
    }
}
