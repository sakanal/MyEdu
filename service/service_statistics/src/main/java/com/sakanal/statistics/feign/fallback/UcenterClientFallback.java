package com.sakanal.statistics.feign.fallback;

import com.sakanal.statistics.feign.UcenterClient;
import com.sakanal.utils.entity.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientFallback implements UcenterClient {
    @Override
    public CommonResult<Integer> countRegister(String day) {
        return new CommonResult<Integer>().SUCCESS(1000);
    }
}
