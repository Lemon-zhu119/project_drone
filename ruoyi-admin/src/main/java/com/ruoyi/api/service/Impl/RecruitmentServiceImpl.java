package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.CityDto;
import com.ruoyi.api.domain.Recruitment;
import com.ruoyi.api.mapper.CityMapper;
import com.ruoyi.api.mapper.RecruitmentMapper;
import com.ruoyi.api.service.CityService;
import com.ruoyi.api.service.RecruitmentService;
import com.ruoyi.api.util.Maputil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.web.controller.institutionmore.domain.Institution;
import com.ruoyi.web.controller.institutionmore.mapper.InstitutionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentMapper recruitmentMapper;
    @Autowired
    private InstitutionMapper institutionMapper;


    @Override
    public List<Recruitment> selectList() {
        List<Recruitment> list=recruitmentMapper.selectList();

        for (Recruitment recruitment:list){
            Institution ins=institutionMapper.selectInstitutionById(recruitment.getInstitutionId());
            if(ins!=null){
                String name=Maputil.getNameByLatAndLog(ins.getLatitude(),ins.getLongitude());
                recruitment.setLocation(name);
            }else {
                throw new ServiceException("当前机构不存在");
            }

            if (recruitment.getTags()!=null&&!recruitment.getTags().isEmpty()){
                recruitment.setTagList(Arrays.asList(recruitment.getTags().split(";")));
            }
        }
        return list;
    }
}
