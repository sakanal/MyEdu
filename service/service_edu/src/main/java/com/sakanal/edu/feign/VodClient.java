package com.sakanal.edu.feign;

import com.sakanal.edu.feign.fallback.VodClientFallback;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "vodService",fallback = VodClientFallback.class)
public interface VodClient {

    @ApiOperation("根据视频id删除阿里云中的视频")
    @DeleteMapping("/vod/file/remove/{videoSourceId}")
    public CommonResult<Boolean> removeVideo(@PathVariable("videoSourceId")String videoSourceId);

    @ApiOperation("删除多个阿里云中的视频")
    @DeleteMapping("/vod/file/batchRemove")
    public CommonResult<Boolean> batchRemoveVideo(@RequestParam("videoIdList") List<String> videoIdList);

    @ApiOperation("根据视频id获取视频凭证")
    @GetMapping("/getPlayAuth/{videoSourceId}")
    public CommonResult<String> getPlayAuth(@ApiParam("视频id") @PathVariable("videoSourceId") String videoSourceId);
}
