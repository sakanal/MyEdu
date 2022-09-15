package com.sakanal.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.base.exception.MyException;
import com.sakanal.edu.entity.Chapter;
import com.sakanal.edu.entity.Course;
import com.sakanal.edu.entity.CourseDescription;
import com.sakanal.edu.entity.Video;
import com.sakanal.edu.entity.vo.CourseInfoForm;
import com.sakanal.edu.entity.vo.CoursePublishVo;
import com.sakanal.edu.mapper.CourseMapper;
import com.sakanal.edu.service.ChapterService;
import com.sakanal.edu.service.CourseDescriptionService;
import com.sakanal.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakanal.edu.service.VideoService;
import com.sakanal.utils.code.ResultCode;
import com.sakanal.utils.code.ResultMessage;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    //课程简介注入
    @Resource
    private CourseDescriptionService courseDescriptionService;
    //课程小节注入
    @Resource
    private VideoService videoService;
    //课程章节注入
    @Resource
    private ChapterService chapterService;

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

    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        CourseInfoForm courseInfoForm = null;
        Course course = this.getById(courseId);
        if (course!=null){
            courseInfoForm = new CourseInfoForm();
            BeanUtils.copyProperties(course,courseInfoForm);
            CourseDescription courseDescription = courseDescriptionService.getById(courseId);
            if (courseDescription!=null){
                courseInfoForm.setDescription(courseDescription.getDescription());
            }
        }
        return courseInfoForm;
    }

    @Override
    public String updateCourseInfo(CourseInfoForm courseInfoForm) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        boolean result = this.updateById(course);
        if (!result){
            throw new MyException(20001,"修改课程信息失败");
        }
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseInfoForm.getId());
        courseDescription.setDescription(courseInfoForm.getDescription());
        long count = courseDescriptionService.count(new QueryWrapper<CourseDescription>().eq("id", courseInfoForm.getId()));
        if (count>0){
            result = courseDescriptionService.updateById(courseDescription);
        }else {
            result = courseDescriptionService.save(courseDescription);
        }
        if (!result){
            throw new MyException(20001,"修改课程简介失败");
        }
        return courseInfoForm.getId();
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        CoursePublishVo publishCourseInfo = courseMapper.getPublishCourseInfo(courseId);
        return courseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public void removeCourseById(String courseId) {
        if (!"".equals(courseId)){
            try {
                //删除课程小节
                QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<Video>().eq("course_id",courseId);
                videoService.remove(videoQueryWrapper);
                //删除课程章节
                QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<Chapter>().eq("course_id", courseId);
                chapterService.remove(chapterQueryWrapper);
                //删除课程简介
                courseDescriptionService.removeById(courseId);
                //删除课程信息
                this.removeById(courseId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(ResultCode.ERROR.getKey(), ResultMessage.ERROR.getMessage());
            }
        }else {
            throw new MyException(20001,"参数错误");
        }
    }
}
