package com.ruoyi.api.service;

import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.Province;

import java.util.List;

public interface ProvinceService {


    List<Province> getProvinceList();

    String selectProvinceNameById(Integer provinceId);
}
