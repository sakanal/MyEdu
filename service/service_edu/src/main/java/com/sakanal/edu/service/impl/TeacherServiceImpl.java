package com.sakanal.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.edu.entity.Course;
import com.sakanal.edu.entity.Teacher;
import com.sakanal.edu.mapper.TeacherMapper;
import com.sakanal.edu.service.CourseService;
import com.sakanal.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-10
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Resource
    private CourseService courseService;

    @Override
    public Map<String, Object> getTeacherFrontPageList(Page<Teacher> teacherPage) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<Teacher>().orderByDesc("sort");
        Page<Teacher> page = this.page(teacherPage, teacherQueryWrapper);
        if (page.getTotal()>0) {
            //把分页的数据获取出来返回一个map集合
            HashMap<String, Object> map = new HashMap<>();
            long total = teacherPage.getTotal();
            long current = teacherPage.getCurrent();
            long size = teacherPage.getSize();
            List<Teacher> teacherList = teacherPage.getRecords();
            long pages = teacherPage.getPages();
            boolean hasPrevious = teacherPage.hasPrevious();
            boolean hasNext = teacherPage.hasNext();

            //将数据封装到map中返回
            //总记录数
            map.put("total",teacherPage.getTotal());
            //当前页
            map.put("current",teacherPage.getCurrent());
            //每页记录数
            map.put("size",teacherPage.getSize());
            //查询到的对象
            map.put("records",teacherPage.getRecords());
            //是否有上一页
            map.put("hasPrevious",teacherPage.hasPrevious());
            //是否有下一页
            map.put("hasNext",teacherPage.hasNext());
            //总页数
            map.put("pages",teacherPage.getPages());

            return map;
        }

        return null;
    }

    @Override
    public HashMap<String, Object> getTeacherInfoAndCourseList(Long teacherId) {
        Teacher teacher = this.getById(teacherId);
        if (teacher!=null){
            HashMap<String, Object> map = new HashMap<>();
            map.put("teacherInfo",teacher);
            List<Course> courseList = courseService.list(new QueryWrapper<Course>().eq("teacher_id", teacherId));
            map.put("courseList",courseList);
            return map;
        }
        return null;
    }
}
