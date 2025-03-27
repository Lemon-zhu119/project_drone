package com.ruoyi.api.mapper;

import com.ruoyi.api.domain.Course;
import com.ruoyi.api.domain.CustomerExamHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {


    List<Course> selectCourseList(Integer institutionId);
    List<Course> selectCourseListByInstitutionAndCourseId(@Param("institutionId") Integer institutionId);
}
