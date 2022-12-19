package com.sakanal.order.service;

import com.sakanal.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-12-05
 */
public interface PayLogService extends IService<PayLog> {

    Map<String, Object> createWxQrcode(String orderNo);
    Map<String, Object> testCreateWxQrcode(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);
    Map<String, String> testQueryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
