package com.sakanal.edu.mapper;

import com.sakanal.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

}
