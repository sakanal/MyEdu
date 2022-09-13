package com.sakanal.edu.controller;

import com.sakanal.edu.service.SubjectService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/edu/subject")
public class SubjectController {
    @Resource
    private SubjectService subjectService;

    @ApiOperation("添加课程")
    @PostMapping("/upload")
    public CommonResult<Boolean> upload(@ApiParam("上传的课程表格") MultipartFile multipartFile){
        subjectService.saveSubject(multipartFile);
        return new CommonResult<Boolean>().SUCCESS();
    }
}
