package com.sakanal.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-10
 */
public interface TeacherService extends IService<Teacher> {

    Map<String, Object> getTeacherFrontPageList(Page<Teacher> teacherPage);

    HashMap<String, Object> getTeacherInfoAndCourseList(Long teacherId);
}
