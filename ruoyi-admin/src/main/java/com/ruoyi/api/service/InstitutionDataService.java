package com.ruoyi.api.service;

import com.ruoyi.api.domain.InstitutionData;
import com.ruoyi.api.domain.dto.CustomerRegisterDto;
import com.ruoyi.api.domain.dto.InstitutionDateDto;
import com.ruoyi.api.domain.dto.QualificationDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InstitutionDataService {


    List<InstitutionDateDto> getInstitutionList(Integer sortType, String city, String subject,Double latitude,Double longitude,String keyword);

    List<InstitutionDateDto> selectListByCity(String city);
    List<InstitutionData> selectInsPic();


    InstitutionData selectListById(Integer id);

    List<InstitutionDateDto> selectListByName(String name,Double latitude,Double longitude);

    List<InstitutionDateDto> selectList();

    List<InstitutionDateDto> selectListByProvince(String province);

    List<InstitutionDateDto> selectListByDistance(Double latitude, Double longitude, String city);

    void insertCustomer(CustomerRegisterDto customerRegisterDto);

//    void qualification(QualificationDto qualificationDto);
}
