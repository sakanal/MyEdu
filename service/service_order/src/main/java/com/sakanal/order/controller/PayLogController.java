package com.sakanal.order.controller;

import com.sakanal.order.service.PayLogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-12-05
 */
@Api(tags = "订单日志管理模块")
@RestController
@RequestMapping("/order/payLog")
public class PayLogController {
    @Resource
    private PayLogService payLogService;

}
