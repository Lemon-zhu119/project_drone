package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.Course;
import com.ruoyi.api.domain.CustomerExamHistory;
import com.ruoyi.api.domain.Options;
import com.ruoyi.api.mapper.CourseMapper;
import com.ruoyi.api.mapper.CustomerExamHistoryMapper;
import com.ruoyi.api.service.CourseService;
import com.ruoyi.api.service.CustomerExamHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> selectCourseList(Integer institutionId) {
        List<Course> list=courseMapper.selectCourseList(institutionId);
        for(Course course:list){
            String tag=course.getTag();
            List<String> Tags=new ArrayList<>();
            StringTokenizer tokenizer=new StringTokenizer(tag,";");
            while (tokenizer.hasMoreTokens()){
                String token=tokenizer.nextToken().trim();
                Tags.add(token);
            }
            course.setTags(Tags);
        }
        return list;
    }

    @Override
    public List<Course> selectCourseListByInstitution(Integer insId) {
        List<Course> list=courseMapper.selectCourseListByInstitutionAndCourseId(insId);
        for(Course course:list){
            String tag=course.getTag();
            List<String> Tags=new ArrayList<>();
            StringTokenizer tokenizer=new StringTokenizer(tag,";");
            while (tokenizer.hasMoreTokens()){
                String token=tokenizer.nextToken().trim();
                Tags.add(token);
            }
            course.setTags(Tags);
        }
        return list;
    }
}
