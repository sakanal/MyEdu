package com.sakanal.edu.controller;

import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "教师登录")
@Slf4j
@RestController
@RequestMapping("/edu/user")
public class TeacherLoginController {

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public CommonResult<Map<String, String>> login(){
        Map<String, String> loginToken = new HashMap<>();
        loginToken.put("token","sakanal");
        return new CommonResult<Map<String, String>>().SUCCESS(loginToken);
    }
    @ApiOperation(value = "获取登录成功后的用户信息信息")
    @GetMapping("/info")
    public CommonResult<Map<String, String>> info(@RequestParam("token")String token){
        if ("sakanal".equals(token)){
            log.info("sakanal 正在登录");
        }
        Map<String, String> info = new HashMap<>();
//        info.put("roles","admin");
        info.put("name","sakanal");
        info.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return new CommonResult<Map<String, String>>().SUCCESS(info);
    }


    @ApiOperation(value = "退出登录用户")
    @PostMapping("/logout")
    public CommonResult<Boolean> logout(){
        return new CommonResult<Boolean>().SUCCESS();
    }

}
