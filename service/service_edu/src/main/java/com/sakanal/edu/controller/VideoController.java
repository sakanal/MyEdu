package com.sakanal.edu.controller;

import com.sakanal.edu.entity.Video;
import com.sakanal.edu.service.VideoService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Api(tags = "课程小节管理")
@RestController
@RequestMapping("/edu/video")
public class VideoController {
    @Resource
    private VideoService videoService;

    @ApiOperation("添加小节")
    @PostMapping("/add")
    public CommonResult<Boolean> add(@RequestBody Video video){
        boolean result = videoService.save(video);
        if (result){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }
    @ApiOperation("根据小节id，获取小节信息")
    @GetMapping("/getById/{videoId}")
    public CommonResult<Video> getById(@PathVariable("videoId")String videoId){
        Video video = videoService.getById(videoId);
        if (video!=null){
            return new CommonResult<Video>().SUCCESS(video);
        }else {
            return new CommonResult<Video>().NO_RESULT_DATA();
        }
    }
//    TODO
    @ApiOperation("修改小节")
    @PutMapping("/update")
    public CommonResult<Boolean> update(@RequestBody Video video){
        boolean result = videoService.updateById(video);
        if (result){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }

    @ApiOperation("删除小节")
    @DeleteMapping("/remove/{videoId}")
    public CommonResult<Boolean> remove(@PathVariable("videoId")String videoId){
        boolean result = videoService.removeById(videoId);
        if (result){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }

}
