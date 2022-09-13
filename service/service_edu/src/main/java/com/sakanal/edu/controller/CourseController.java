package com.sakanal.edu.controller;

import com.sakanal.edu.entity.Course;
import com.sakanal.edu.entity.vo.CourseInfoForm;
import com.sakanal.edu.service.CourseService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/edu/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    @ApiOperation("添加课程的基本信息")
    @PostMapping("/add")
    public CommonResult<String> addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return new CommonResult<String>().SUCCESS(courseId);
    }

    @ApiOperation("根据id获取课程的基本信息")
    @GetMapping("/getById/{id}")
    public CommonResult getCourseById(@PathVariable("id")String id){
        Course course = courseService.getById(id);
        return new CommonResult().SUCCESS();
    }
}
