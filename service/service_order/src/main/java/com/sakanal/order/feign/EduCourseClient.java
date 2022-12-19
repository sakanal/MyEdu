package com.sakanal.order.feign;

import com.sakanal.base.bean.vo.CourseVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(name = "eduService")
public interface EduCourseClient {

    //根据课程id，获取课程信息
    @PostMapping("/edu/course/getCourseInfoByIdOrder/{courseId}")
    public CourseVoOrder getCourseInfoByIdOrder(@PathVariable("courseId") Long courseId);

}

