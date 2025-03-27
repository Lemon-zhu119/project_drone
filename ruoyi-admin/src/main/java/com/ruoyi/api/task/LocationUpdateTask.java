package com.ruoyi.api.task;

import com.ruoyi.api.util.Maputil;
import com.ruoyi.web.controller.institutionmore.domain.Institution;
import com.ruoyi.web.controller.institutionmore.mapper.InstitutionMapper;
import com.ruoyi.web.controller.institutionmore.service.impl.InstitutionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class LocationUpdateTask {
    private static final Logger log= LoggerFactory.getLogger(LocationUpdateTask.class);
    @Autowired
    private InstitutionMapper institutionMapper;
    @Scheduled(cron = "0 */5 * * * ?")
    public void updateInstitutionLocation(){
        try{
            Institution institution=new Institution();
            List<Institution> list=institutionMapper.selectInstitutionList(institution);
            List<Institution> institutionsToUpdate=list.stream().filter(institution1 ->
                    institution1.getLongitude()==null||institution1.getLatitude()==null||institution1.getUpdateTime()==null)
                    .sorted(Comparator.comparing(Institution::getUpdateTime,Comparator.nullsFirst(Comparator.naturalOrder())))
                    .limit(3).collect(Collectors.toList());
            for(Institution ins:institutionsToUpdate){
                Map<String,Double> coordinates= Maputil.getLatAndLogByName(ins.getBase());
                if(!coordinates.isEmpty()){
                    ins.setLatitude(coordinates.get("latitude"));
                    ins.setLongitude(coordinates.get("longitude"));
                    ins.setUpdateTime(new Date());
                    institutionMapper.updateInstitution(ins);
                    log.info("成功更新机构经纬度",ins.getId());
                }else {
                    log.warn("未能获取机构的信息",ins.getId());
                }
                Thread.sleep(1000);
            }
        }catch (Exception e){
            log.error("更新机构失败",e.getMessage());
        }

    }
}
