package com.sakanal.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sakanal.edu.entity.vo.CourseFrontVo;
import com.sakanal.edu.entity.vo.CourseInfoForm;
import com.sakanal.edu.entity.vo.CoursePublishVo;
import com.sakanal.edu.entity.vo.CourseWebVo;

import java.util.HashMap;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfo(String courseId);

    String updateCourseInfo(CourseInfoForm courseInfoForm);

    //根据课程id查询课程确认信息
    public CoursePublishVo getPublishCourseInfo(String courseId);

    void removeCourseById(String courseId);

    HashMap<String, Object> getCourseList(Page<Course> coursePage, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(Long courseId);
}
