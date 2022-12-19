package com.sakanal.order.service.impl;

import com.sakanal.base.bean.vo.CourseVoOrder;
import com.sakanal.base.bean.vo.UcenterMemberVoOrder;
import com.sakanal.order.entity.Order;
import com.sakanal.order.feign.EduCourseClient;
import com.sakanal.order.feign.UcenterMemberClient;
import com.sakanal.order.mapper.OrderMapper;
import com.sakanal.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakanal.order.utils.OrderNoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-12-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private EduCourseClient eduCourseClient;
    @Resource
    private UcenterMemberClient ucenterMemberClient;

    @Override
    public String createOrders(Long courseId, String userId) {
        //根据用户id，获取用户信息
        UcenterMemberVoOrder userInfo = ucenterMemberClient.getUserInfoById(userId);

        //根据课程id，获取课程信息
        CourseVoOrder courseInfo = eduCourseClient.getCourseInfoByIdOrder(courseId);

        Order order = new Order();
        order.setMobile(userInfo.getMobile());
        order.setNickname(userInfo.getNickname());
        order.setMemberId(userId);
        order.setCourseCover(courseInfo.getCover());
        order.setCourseId(String.valueOf(courseId));
        order.setCourseTitle(courseInfo.getTitle());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setTotalFee(courseInfo.getPrice());
        order.setStatus(0);//支付状态：（ 0：已支付，1：未支付 ）
        order.setPayType(1);//支付类型： 1：微信 ， 2：支付宝
        order.setOrderNo(OrderNoUtil.getOrderNo()); //订单号

        //保存订单
        this.save(order);

        //返回订单号
        return order.getOrderNo();
    }
}
