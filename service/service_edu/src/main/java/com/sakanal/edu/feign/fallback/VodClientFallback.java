package com.sakanal.edu.feign.fallback;

import com.sakanal.edu.feign.VodClient;
import com.sakanal.utils.entity.CommonResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientFallback implements VodClient {
    @Override
    public CommonResult<Boolean> removeVideo(String videoSourceId) {
        return new CommonResult<Boolean>().ERROR("删除视频失败");
    }

    @Override
    public CommonResult<Boolean> batchRemoveVideo(List<String> videoIdList) {
        return new CommonResult<Boolean>().ERROR("批量删除视频失败");
    }
}
