package com.sakanal.edu.service;

import com.sakanal.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sakanal.edu.entity.vo.CourseInfoForm;

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
}
