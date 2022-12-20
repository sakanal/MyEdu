package com.sakanal.statistics.mapper;

import com.sakanal.base.bean.entity.Daily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author sakanal
 * @since 2022-12-19
 */
@Mapper
public interface DailyMapper extends BaseMapper<Daily> {

}
