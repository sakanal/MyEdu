package com.sakanal.oss.controller;

import com.sakanal.oss.service.OssService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/oss/file")
@CrossOrigin
public class OssController {

    @Resource
    private OssService ossService;

    @ApiOperation("上传头像")
    @PostMapping
    public CommonResult<String> uploadOssFile(MultipartFile multipartFile){
        String url=ossService.uploadFileAvatar(multipartFile);
        if(StringUtils.hasText(url)){
            return new CommonResult<String>().SUCCESS(url);
        }else {
            return new CommonResult<String>().ERROR();
        }
    }
}
