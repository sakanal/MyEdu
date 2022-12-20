package com.sakanal.ucenter.service;

import com.sakanal.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sakanal.ucenter.vo.LoginVo;
import com.sakanal.ucenter.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-19
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    Integer getCountRegister(String day);
}
