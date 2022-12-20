package com.sakanal.statistics.controller;

import com.sakanal.statistics.service.DailyService;
import com.sakanal.utils.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-12-19
 */
@RestController
@RequestMapping("/statistics/daily")
public class DailyController {


    @Resource
    private DailyService dailyService;

    //统计某一天注册人数
    @PostMapping("/createStatisticsByDay/{day}")
    public CommonResult<Boolean> createStatisticsByDay(@PathVariable String day){
        dailyService.createStatisticsByDay(day);
        return new CommonResult<Boolean>().SUCCESS();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("/showData/{type}/{begin}/{end}")
    public CommonResult<Map<String,Object>> showData(@PathVariable String type, @PathVariable String begin, @PathVariable String end){

        Map<String,Object> map = dailyService.getShowData(type,begin,end);

        return new CommonResult<Map<String,Object>>().SUCCESS(map);
    }
}
