package com.sakanal.order.mapper;

import com.sakanal.order.entity.PayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author sakanal
 * @since 2022-12-05
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {

}
