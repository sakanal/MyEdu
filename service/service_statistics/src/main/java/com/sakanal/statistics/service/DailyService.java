package com.sakanal.statistics.service;

import com.sakanal.base.bean.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-12-19
 */
public interface DailyService extends IService<Daily> {

    void createStatisticsByDay(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
