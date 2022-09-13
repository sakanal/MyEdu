package com.sakanal.edu.service.impl;

import com.sakanal.base.exception.MyException;
import com.sakanal.edu.entity.Course;
import com.sakanal.edu.entity.CourseDescription;
import com.sakanal.edu.entity.vo.CourseInfoForm;
import com.sakanal.edu.mapper.CourseMapper;
import com.sakanal.edu.service.CourseDescriptionService;
import com.sakanal.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    //课程描述注入
    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //向课程表里面添加课程基本信息
        //CourseInfoForm对象 转换成 Course对象
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        int result = baseMapper.insert(course);

        if (result<=0){//表示添加失败
            throw new MyException(20001,"添加课程信息失败");
        }

        String courseId = course.getId();
        //想课程简介表里面添加课程简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(courseId);
        courseDescriptionService.save(courseDescription);

        return courseId;
    }
}
