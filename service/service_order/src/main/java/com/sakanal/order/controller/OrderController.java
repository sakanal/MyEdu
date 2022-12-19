package com.sakanal.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.base.exception.MyException;
import com.sakanal.order.entity.Order;
import com.sakanal.order.service.OrderService;
import com.sakanal.utils.entity.CommonResult;
import com.sakanal.utils.utils.JwtUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-12-05
 */
@Api(tags = "订单管理模块")
@RestController
@RequestMapping("/order/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    //生成订单的方法
    @PostMapping("/createOrder/{courseId}")
    public CommonResult<String> createOrder(@PathVariable Long courseId, HttpServletRequest request){
        //从请求头中获取用户id
        String userId = JwtUtil.getUserIdByJwtToken(request);
        //判断是否登录
        if (StringUtils.isEmpty(userId)){
            throw new MyException(20001,"请登录");
        }

        //生成订单，并生成对应的订单号
        String orderNo = orderService.createOrders(courseId,userId);

        return new CommonResult<String>().SUCCESS(orderNo);
    }

    //根据订单号查询订单信息
    @GetMapping("/getOrderInfoById/{orderNo}")
    public CommonResult<Order> getOrderInfoById(@PathVariable String orderNo){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        return new CommonResult<Order>().SUCCESS(order);
    }


    //根据【用户id、课程id】查询订单表中的状态
    @GetMapping("/isBuyCourse/{memberId}/{courseId}")
    public Boolean isBuyCourse(@PathVariable String memberId,@PathVariable String courseId)	  {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 【1】代表已支付
        long result = orderService.count(wrapper);

        //已支付
        return result > 0;
    }

}
