package com.sakanal.oss.controller;

import com.sakanal.oss.service.OssService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(tags = "上传管理")
@Slf4j
@RestController
@RequestMapping("/oss/file")
public class OssController {

    @Resource
    private OssService ossService;

    @ApiOperation("上传头像")
    @PostMapping("/avatar/{userName}")
    public CommonResult<String> uploadOssFile(@PathVariable("userName") String userName,
                                               MultipartFile multipartFile){
        log.info("开始上传头像");
        String url=ossService.uploadFileAvatar(userName,multipartFile);
        if(StringUtils.hasText(url)){
            return new CommonResult<String>().SUCCESS(url);
        }else {
            return new CommonResult<String>().ERROR();
        }
    }
}
