package com.sakanal.ucenter.controller;

import com.sakanal.ucenter.service.UcenterMemberService;
import com.sakanal.ucenter.vo.LoginVo;
import com.sakanal.ucenter.vo.RegisterVo;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-09-19
 */
@Api(tags = "登录注册管理")
@Slf4j
@RestController
@RequestMapping("/ucenter/ucenterMember")
public class UcenterMemberController {
    @Resource
    private UcenterMemberService ucenterMemberService;
    //登录
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginVo loginVo){
        //返回token，使用jwt生成
        String token = ucenterMemberService.login(loginVo);
        return new CommonResult<String>().SUCCESS(token);
    }

    //注册
    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return new CommonResult<String>().SUCCESS();
    }
}
