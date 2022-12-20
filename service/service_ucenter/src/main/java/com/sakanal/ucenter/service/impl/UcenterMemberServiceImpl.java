package com.sakanal.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.base.exception.MyException;
import com.sakanal.ucenter.entity.UcenterMember;
import com.sakanal.ucenter.mapper.UcenterMemberMapper;
import com.sakanal.ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakanal.ucenter.vo.LoginVo;
import com.sakanal.ucenter.vo.RegisterVo;
import com.sakanal.utils.utils.JwtUtil;
import com.sakanal.utils.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-19
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        //获取手机号和密码
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();
        //判断输入的手机号和密码是否为空
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(phone)){
            throw new MyException(20001,"手机号或密码为空");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",phone);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (ucenterMember == null){
            throw new MyException(20001,"手机号不存在");
        }

        //判断密码是否正确
        // MD5加密是不可逆性的，不能解密，只能加密
        //将获取到的密码经过MD5加密与数据库比较
//        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())){
//            throw new MyException(20001,"密码不正确");
//        }
        if (!MD5Util.getMD5(password).equals(ucenterMember.getPassword())){
            throw new MyException(20001,"密码不正确");
        }

        //判断用户是否禁用
        if (ucenterMember.getIsDisabled()){
            throw new MyException(20001,"用户被禁用");
        }

        //生成jwtToken
        return JwtUtil.getUserJwtToken(ucenterMember.getId(), ucenterMember.getNickname());

    }

    //注册的方法
    @Override
    public void register(RegisterVo registerVo) {
        //获取前端传来的数据
        String nickname = registerVo.getNickname(); //昵称
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String password = registerVo.getPassword(); //密码

        //非空判断
        if (StringUtils.isEmpty(nickname)
                ||StringUtils.isEmpty(code)
                ||StringUtils.isEmpty(mobile)
                ||StringUtils.isEmpty(password)){
            throw new MyException(20001,"传来的数据有空值，注册失败");
        }

        //判断验证码
        //获取redis验证码，根据手机号获取
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)){
            throw new MyException(20001,"验证码过期");
        }
        //阿昌阿里云没审核通过，尴尬，就先直接定死一个验证码好了
//        if (!code.equals("746077")){
//            throw new MyException(20001,"验证码不正确，注册失败");
//        }

        //手机号不能重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Long count = baseMapper.selectCount(wrapper);
        if (count>=1){
            throw new MyException(20001,"手机号重复，注册失败");
        }

        //数据添加到数据库中
        UcenterMember member = new UcenterMember();
        member.setPassword(MD5Util.getMD5(password));//密码加密
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/65423f14-49a9-4092-baf5-6d0ef9686a85.png");
        baseMapper.insert(member);

    }

    @Override
    public Integer getCountRegister(String day) {
        return baseMapper.getCountRegister(day);
    }
}
