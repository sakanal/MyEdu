package com.sakanal.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.edu.entity.Teacher;
import com.sakanal.edu.service.TeacherService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "前台教师管理")
@RestController
@RequestMapping("/edu/front/teacher")
public class TeacherFrontController {
    @Resource
    private TeacherService teacherService;

    @ApiOperation("前台分页查询教师")
    @GetMapping("/pageFind/{page}/{size}")
    public CommonResult< Map<String, Object>> pageFind(@ApiParam("页码") @PathVariable("page")Integer page,
                                                       @ApiParam("页码容量") @PathVariable("size")Integer size){
        Page<Teacher> teacherPage = new Page<>(page,size);
        Map<String, Object> result =  teacherService.getTeacherFrontPageList(teacherPage);
        if (result!=null){
            return new CommonResult< Map<String, Object>>().SUCCESS(result);
        }
        return new CommonResult< Map<String, Object>>().NO_RESULT_DATA();
    }

    @ApiOperation("根据教师id获取教师信息以及该教师所教授的所有课程")
    @GetMapping("/getTeacherInfoAndCourseListByTeacherId/{teacherId}")
    public CommonResult<HashMap<String,Object>> getTeacherInfoAndCourseListByTeacherId(@ApiParam("教师id")@PathVariable("teacherId")Long teacherId){
        HashMap<String,Object> result = teacherService.getTeacherInfoAndCourseList(teacherId);

        if (result!=null){
            return new CommonResult<HashMap<String,Object>>().SUCCESS(result);
        }else {
            return new CommonResult<HashMap<String,Object>>().NO_RESULT_DATA();
        }
    }

}
