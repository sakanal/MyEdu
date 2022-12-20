package com.sakanal.ucenter.mapper;

import com.sakanal.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author sakanal
 * @since 2022-09-19
 */
@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer getCountRegister(String day);
}
