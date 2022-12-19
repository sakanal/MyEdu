package com.sakanal.order.service;

import com.sakanal.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-12-05
 */
public interface OrderService extends IService<Order> {

    String createOrders(Long courseId, String userId);
}
