package com.sakanal.statistics.feign;

import com.sakanal.statistics.feign.fallback.UcenterClientFallback;
import com.sakanal.utils.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "ucenterService",fallback = UcenterClientFallback.class)
public interface UcenterClient {
    //根据日期，获取那天注册人数
    @GetMapping("/ucenter/ucenterMember/countRegister/{day}")
    public CommonResult<Integer> countRegister(@PathVariable String day);
}

