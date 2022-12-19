package com.sakanal.order.feign;

import com.sakanal.base.bean.vo.UcenterMemberVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "ucenterService")
public interface UcenterMemberClient {
    @PostMapping("/ucenter/ucenterMember/getUserInfoById/{userId}")
    public UcenterMemberVoOrder getUserInfoById(@PathVariable("userId") String userId);
}
