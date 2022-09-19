package com.sakanal.msm.controller;

import com.sakanal.msm.service.MsmService;
import com.sakanal.utils.entity.CommonResult;
import com.sakanal.utils.utils.RandomUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Api(tags = "短信管理")
@Slf4j
@RestController
@RequestMapping("/msm/message")
public class MsmController {
    @Resource
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信的方法
    @GetMapping("/send/{phone}")
    public CommonResult<Boolean> sendMsm(@PathVariable String phone){
        //从redis获取验证码，如果能获取，直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            log.info("redis中有值");
            return new CommonResult<Boolean>().SUCCESS();
        }

        //获取不到就阿里云发送
        //生成随机值，并传递给阿里云短信，让他转发给手机
        code = RandomUtil.getSixBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);

        //调用service中发送短信的方法
        boolean isSend = msmService.sendMsm(map, phone);
        if (isSend){
            //如果发送成功，把发送成功的code验证码保存到redis中，并设置有效时间，设置5分钟过期
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }

    }
}
