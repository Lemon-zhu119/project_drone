package com.ruoyi.api.service;

import com.ruoyi.api.domain.Course;
import com.ruoyi.api.domain.CustomerExamHistory;

import java.util.List;

public interface CourseService {


    List<Course> selectCourseList(Integer institutionId);


    List<Course> selectCourseListByInstitution(Integer id);
}
