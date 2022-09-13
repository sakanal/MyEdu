package com.sakanal.edu.mapper;

import com.sakanal.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

}
