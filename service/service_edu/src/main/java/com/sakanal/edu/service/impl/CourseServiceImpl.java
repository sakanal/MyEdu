package com.sakanal.edu.service.impl;

import com.sakanal.edu.entity.Course;
import com.sakanal.edu.mapper.CourseMapper;
import com.sakanal.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
