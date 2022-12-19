package com.sakanal.order.service.impl;

import com.sakanal.order.entity.PayLog;
import com.sakanal.order.mapper.PayLogMapper;
import com.sakanal.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-12-05
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
