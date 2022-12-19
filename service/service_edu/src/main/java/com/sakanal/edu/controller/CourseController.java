package com.sakanal.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.base.bean.vo.CourseVoOrder;
import com.sakanal.edu.entity.Course;
import com.sakanal.edu.entity.vo.*;
import com.sakanal.edu.service.CourseService;
import com.sakanal.utils.entity.CommonResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
@Slf4j
@RestController
@RequestMapping("/edu/course")
@Transactional
public class CourseController {
    @Resource
    private CourseService courseService;

    @ApiOperation("分页查询")
    @PostMapping("/pageFind/{current}")
    public CommonResult<Page<Course>> pageFind(@ApiParam("页码")@PathVariable("current")Integer current,
                                               @RequestBody CourseQuery courseQuery){
        Page<Course> coursePage = new Page<>(current,10);
        if (courseQuery!=null){
            QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
            String title = courseQuery.getTitle();
            Integer price = courseQuery.getPrice();
            String status = courseQuery.getStatus();
            String startTime = courseQuery.getStartTime();
            String endTime = courseQuery.getEndTime();
            if (StringUtils.hasText(title)){
                courseQueryWrapper.like("title",title);
            }
            if (!StringUtils.isEmpty(price)) {
                courseQueryWrapper.le("price",price);
            }
            if (StringUtils.hasText(status)){
                courseQueryWrapper.eq("status",status);
            }
            if (StringUtils.hasText(startTime)){
                courseQueryWrapper.ge("gmt_create",startTime);
            }
            if (StringUtils.hasText(endTime)){
                courseQueryWrapper.le("gmt_create",endTime);
            }

            courseQueryWrapper.orderByDesc("gmt_modified");
            courseService.page(coursePage,courseQueryWrapper);
        }else {
            courseService.page(coursePage);
        }
        if (coursePage.getRecords().size()>0){
            return new CommonResult<Page<Course>>().SUCCESS(coursePage);
        }else {
            return new CommonResult<Page<Course>>().NO_RESULT_DATA();
        }
    }

    @ApiOperation("添加课程的基本信息")
    @PostMapping("/add")
    public CommonResult<String> addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return new CommonResult<String>().SUCCESS(courseId);
    }

    @ApiOperation("根据id获取课程的基本信息")
    @GetMapping("/getById/{courseId}")
    public CommonResult<CourseInfoForm> getById(@PathVariable("courseId")String courseId){
        CourseInfoForm courseInfoForm = courseService.getCourseInfo(courseId);
        if (courseInfoForm!=null){
            return new CommonResult<CourseInfoForm>().SUCCESS(courseInfoForm);
        }else {
            return new CommonResult<CourseInfoForm>().NO_RESULT_DATA();
        }
    }
    @ApiOperation("修改课程的基本信息")
    @PutMapping("/update")
    public CommonResult<String> updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.updateCourseInfo(courseInfoForm);
        if (courseId!=null && !courseId.equals("")){
            return new CommonResult<String>().SUCCESS(courseId);
        }else {
            return new CommonResult<String>().ERROR();
        }
    }

    @ApiOperation("根据课程id查询课程确认信息")
    @GetMapping("/getPublishCourseInfo/{courseId}")
    public CommonResult<CoursePublishVo> getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo publishCourseInfo = courseService.getPublishCourseInfo(courseId);
        if (publishCourseInfo!=null){
            return new CommonResult<CoursePublishVo>().SUCCESS(publishCourseInfo);
        }else {
            return new CommonResult<CoursePublishVo>().ERROR();
        }
    }

    @ApiOperation("课程的最终发布，将课程的status，从Draft改为Normal")
    @PutMapping("/publishCourse/{courseId}")
    public CommonResult<Boolean> publishCourse(@PathVariable("courseId")String courseId){
        Course course = new Course();
        course.setId(courseId);
        course.setStatus("Normal");
        boolean result = courseService.updateById(course);
        if (result){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }

    @ApiOperation("根据课程id，删除课程，逻辑删除")
    @DeleteMapping("/remove/{courseId}")
    public  CommonResult<Boolean> remove(@PathVariable("courseId")String courseId){
        courseService.removeCourseById(courseId);
        return new CommonResult<Boolean>().SUCCESS();
    }


    //根据课程id，查询课程信息【订单】
    @PostMapping("/getCourseInfoByIdOrder/{courseId}")
    public CourseVoOrder getCourseInfoByIdOrder(@PathVariable Long courseId){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(courseId);

        CourseVoOrder eduCourseVo = new CourseVoOrder();
        BeanUtils.copyProperties(courseInfo,eduCourseVo);

        return eduCourseVo;

    }

}
