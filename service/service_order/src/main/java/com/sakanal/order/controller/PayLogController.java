package com.sakanal.order.controller;

import com.sakanal.order.service.PayLogService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

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

    //根据订单号，生成微信支付二维码的接口
    @GetMapping("/createWxQRcode/{orderNo}")
    public CommonResult<Map<String,Object>> createWxQRcode(@PathVariable String orderNo){
        //返回信息，包含二维码地址、其他信息
//        Map<String, Object> map = payLogService.createWxQrcode(orderNo);
        Map<String, Object> map = payLogService.testCreateWxQrcode(orderNo);
        return new CommonResult<Map<String,Object>>().SUCCESS(map);
    }
    //根据订单号查询订单支付状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public CommonResult<String> queryPayStatus(@PathVariable String orderNo){

//        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        Map<String,String> map = payLogService.testQueryPayStatus(orderNo);
        if (map==null){
            return new CommonResult<String>().ERROR("支付出错了");
        }
        //如果返回的map不为空，通过map获取订单的状态
        if (map.get("trade_state").equals("SUCCESS")){ //支付成功
            //添加记录到支付表里，并更新订单表的状态
            payLogService.updateOrderStatus(map);
            return new CommonResult<String>().SUCCESS("支付成功");
        }

        return new CommonResult<String>().SUCCESS("支付中");
    }
}
