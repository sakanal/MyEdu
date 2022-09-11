package com.sakanal.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.base.exception.MyException;
import com.sakanal.edu.entity.Teacher;
import com.sakanal.edu.entity.vo.TeacherQuery;
import com.sakanal.edu.service.TeacherService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-09-10
 */
@Slf4j
@Api(tags = "教师管理")
@RestController
@RequestMapping("/edu/teacher")
@Transactional
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    @ApiOperation(value = "所有教师列表")
    @GetMapping("/findAll")
    public CommonResult<List<Teacher>> findAll(){
        List<Teacher> teacherList = teacherService.list(null);
//        try {
//            int i=1/0;
//        } catch (Exception e) {
//            throw new MyException(444,"执行了自定义异常处理");
//        }
        if (teacherList.size()>0){
            return new CommonResult<List<Teacher>>().SUCCESS(teacherList);
        }else {
            return new CommonResult<List<Teacher>>().ERROR();
        }

    }

    @ApiOperation(value = "根据id逻辑删除")
    @DeleteMapping("/delete/{id}")
    public CommonResult<Boolean> remove(@ApiParam(value = "教师id",required = true) @PathVariable("id") String id){
        boolean removeStatus = teacherService.removeById(id);
        if (removeStatus){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("/pageFind/{current}")
    public CommonResult<Page<Teacher>> pageFind(@ApiParam("页码")@PathVariable("current")Integer current,
                                                @RequestBody TeacherQuery teacherQuery){
        Page<Teacher> teacherPage = new Page<>(current,10);
        if (teacherQuery!=null){
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            String name = teacherQuery.getName();
            Integer level = teacherQuery.getLevel();
            String startTime = teacherQuery.getStartTime();
            String endTime = teacherQuery.getEndTime();
            if (StringUtils.hasText(name))
                queryWrapper.like("name",name);
            if (!StringUtils.isEmpty(level))
                queryWrapper.eq("level",level);
            if (StringUtils.hasText(startTime))
                queryWrapper.ge("gmt_create",startTime);
            if (StringUtils.hasText(endTime))
                queryWrapper.le("gmt_create",endTime);

            teacherService.page(teacherPage,queryWrapper);
        }else {
            teacherService.page(teacherPage);
        }

        if (teacherPage.getRecords().size()>0){
            return new CommonResult<Page<Teacher>>().SUCCESS(teacherPage);
        }else {
            return new CommonResult<Page<Teacher>>().NO_RESULT_DATA();
        }
    }

    @ApiOperation(value = "添加教师")
    @PostMapping("/addTeacher")
    public CommonResult<Boolean> add(@ApiParam(value = "教师信息")@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }

    @ApiOperation(value = "根据教师id查询教师信息")
    @GetMapping("/getById/{id}")
    public CommonResult<Teacher> getById(@ApiParam("教师id")@PathVariable(value = "id")String id){
        if (StringUtils.hasText(id)){
            Teacher teacher = teacherService.getById(id);
            if (teacher!=null){
                return new CommonResult<Teacher>().SUCCESS(teacher);
            }else {
                return new CommonResult<Teacher>().NO_RESULT_DATA();
            }
        }else {
            return new CommonResult<Teacher>().NO_PARAMETERS();
        }
    }

    @ApiOperation("根据id修改教师信息")
    @PutMapping("/updateById")
    public CommonResult<Boolean> updateById(@RequestBody Teacher teacher){
        log.info(teacher.getId());
        boolean flag = teacherService.updateById(teacher);
        if (flag){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }
}
