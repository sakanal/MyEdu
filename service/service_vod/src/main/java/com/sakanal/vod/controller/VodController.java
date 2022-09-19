package com.sakanal.vod.controller;

import com.sakanal.utils.entity.CommonResult;
import com.sakanal.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "视频管理")
@Slf4j
@RestController
@RequestMapping("/vod/file")
public class VodController {
    @Resource
    private VodService vodService;
    @ApiOperation("测试跨域")
    @PostMapping("/testCors")
    public CommonResult<String> testCors(){
        log.error("测试跨域");
        return new CommonResult<String>().SUCCESS("测试跨域");
    }

    @ApiOperation("上传视频到阿里云")
    @PostMapping("/uploadVideo")
    public CommonResult<String> uploadVideo(MultipartFile multipartFile){
        log.info("开始上传视频");
        String videoId = vodService.uploadVideo(multipartFile);
        return new CommonResult<String>().SUCCESS(videoId);
    }

    @ApiOperation("根据视频id删除阿里云中的视频")
    @DeleteMapping("/remove/{videoSourceId}")
    public CommonResult<Boolean> removeVideo(@PathVariable("videoSourceId")String videoSourceId){
        log.info("开始删除视频");
        vodService.removeVideo(videoSourceId);
        return new CommonResult<Boolean>().SUCCESS();
    }

    @ApiOperation("删除多个阿里云中的视频")
    @DeleteMapping("/batchRemove")
    public CommonResult<Boolean> batchRemoveVideo(@RequestParam("videoIdList")List<String> videoIdList){
        vodService.batchRemoveVideo(videoIdList);
        return new CommonResult<Boolean>().SUCCESS();
    }
}
