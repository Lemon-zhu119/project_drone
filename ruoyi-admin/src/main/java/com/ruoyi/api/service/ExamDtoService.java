package com.ruoyi.api.service;

import com.ruoyi.api.domain.dto.ExamDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamDtoService {
    List<ExamDto> selectExamListByCustomerId(Long customerId);
}
