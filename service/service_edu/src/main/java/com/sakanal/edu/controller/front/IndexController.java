package com.sakanal.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.edu.controller.CourseController;
import com.sakanal.edu.controller.TeacherController;
import com.sakanal.edu.entity.Course;
import com.sakanal.edu.entity.Teacher;
import com.sakanal.edu.service.CourseService;
import com.sakanal.edu.service.TeacherService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "前台页面管理")
@Slf4j
@RestController
@RequestMapping("/edu/index")
public class IndexController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseService courseService;

    @ApiOperation("获取前4条教师数据，根据id降序排序")
    @GetMapping("/teacher")
    @Cacheable(value = "teacher", key = "'indexHotTeacher'")
    public CommonResult<List<Teacher>> indexTeacher(){
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<Teacher>().orderByDesc("id").last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherQueryWrapper);
        if (teacherList.size()>0){
            return new CommonResult<List<Teacher>>().SUCCESS(teacherList);
        }else {
            return new CommonResult<List<Teacher>>().NO_RESULT_DATA();
        }
    }
    @ApiOperation("获取前8条课程数据，根据id降序排序")
    @GetMapping("/course")
    @Cacheable(value = "course",key = "'indexHotCourse'")
    public CommonResult<List<Course>> indexCourse(){
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<Course>().orderByDesc("id").last("limit 8");
        courseQueryWrapper.select(Course.class, info -> !info.getColumn().equals("version"));
        List<Course> courseList = courseService.list(courseQueryWrapper);
        if (courseList.size()>0){
            return new CommonResult<List<Course>>().SUCCESS(courseList);
        }else {
            return new CommonResult<List<Course>>().NO_RESULT_DATA();
        }
    }
}
