package com.sakanal.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakanal.edu.entity.Course;
import com.sakanal.edu.entity.result.ResultChapter;
import com.sakanal.edu.entity.vo.CourseFrontVo;
import com.sakanal.edu.entity.vo.CourseWebVo;
import com.sakanal.edu.feign.OrderClient;
import com.sakanal.edu.service.ChapterService;
import com.sakanal.edu.service.CourseService;
import com.sakanal.utils.entity.CommonResult;
import com.sakanal.utils.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "前台课程管理")
@RestController
@RequestMapping("/edu/front/course")
public class CourseFrontController {
    @Resource
    private CourseService courseService;
    @Resource
    private ChapterService chapterService;
    @Resource
    private OrderClient orderClient;

    @ApiOperation("根据条件查询课程")
    @PostMapping("/getCourseList/{page}/{size}")
    public CommonResult<HashMap<String,Object>> getCourseList(@ApiParam("页码")@PathVariable("page")Integer page,
                                                    @ApiParam("页容量")@PathVariable("size") Integer size,
                                                    @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<Course> coursePage = new Page<>(page, size);
        HashMap<String,Object> result = courseService.getCourseList(coursePage,courseFrontVo);
        return new CommonResult<HashMap<String,Object>>().SUCCESS(result);
    }
    @ApiOperation("根据课程id获取课程信息以及课程小节信息")
    @GetMapping("/getCourseInfoAndChapterInfoByCourseId/{courseId}")
    public CommonResult<Map<String,Object>> getCourseInfoAndChapterInfoByCourseId(@ApiParam("课程id") @PathVariable("courseId") Long courseId,
                                                                                  HttpServletRequest httpServletRequest){
        HashMap<String, Object> map = new HashMap<>();
        //获取章节信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        map.put("courseInfo",courseWebVo);
        //获取小节信息
        List<ResultChapter> allChapterByCourseId = chapterService.getAllChapterByCourseId(String.valueOf(courseId));
        map.put("chapterVideoList",allChapterByCourseId);

        //获取用户id
        String memberId = JwtUtil.getUserIdByJwtToken(httpServletRequest);

        boolean isBuyCourse=false;
        if (!StringUtils.isEmpty(memberId)){
            //根据课程id、用户id，查询课程是否已经购买
            isBuyCourse = orderClient.isBuyCourse(memberId, String.valueOf(courseId));
        }
        map.put("isBuyCourse",isBuyCourse);
        return new CommonResult<Map<String,Object>>().SUCCESS(map);
    }

}
