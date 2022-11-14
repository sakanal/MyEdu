package com.sakanal.ucenter.controller;

import com.sakanal.ucenter.entity.UcenterMember;
import com.sakanal.ucenter.service.UcenterMemberService;
import com.sakanal.ucenter.vo.LoginVo;
import com.sakanal.ucenter.vo.RegisterVo;
import com.sakanal.utils.entity.CommonResult;
import com.sakanal.utils.utils.JwtUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    // 根据token获取用户信息
    @GetMapping("/getUserInfo")
    public CommonResult<UcenterMember> getUserInfo(HttpServletRequest request){
        String userId = JwtUtil.getUserIdByJwtToken(request);
        if (StringUtils.hasText(userId)){
            UcenterMember ucenterMember = ucenterMemberService.getById(userId);
            if (ucenterMember!=null){
                return new CommonResult<UcenterMember>().SUCCESS(ucenterMember);
            }else {
                return new CommonResult<UcenterMember>().ERROR("登录信息无效");
            }
        }else {
            return new CommonResult<UcenterMember>().ERROR("请先登录");
        }

    }
}
