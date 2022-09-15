package com.sakanal.edu.controller;

import com.sakanal.edu.entity.Chapter;
import com.sakanal.edu.entity.result.ResultChapter;
import com.sakanal.edu.service.ChapterService;
import com.sakanal.utils.entity.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Api(tags = "课程章节管理")
@Slf4j
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @ApiOperation("根据课程id获取课程大纲")
    @GetMapping("/getAllById/{courseId}")
    public CommonResult<List<ResultChapter>> getAllById(@PathVariable("courseId")String courseId){
        List<ResultChapter> allChapter = chapterService.getAllChapterByCourseId(courseId);
        if (allChapter!=null && allChapter.size()>0){
            return new CommonResult<List<ResultChapter>>().SUCCESS(allChapter);
        }else {
            return new CommonResult<List<ResultChapter>>().SUCCESS();
        }
    }

    @ApiOperation("添加章节")
    @PostMapping("/add")
    public CommonResult<Boolean> addChapter(@RequestBody Chapter chapter){
        boolean save = chapterService.save(chapter);
        if (save){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }
    @ApiOperation("根据章节id，获取章节信息")
    @GetMapping("/getById/{chapterId}")
    public CommonResult<Chapter> getById(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        if(chapter!=null){
            return new CommonResult<Chapter>().SUCCESS(chapter);
        }else {
            return new CommonResult<Chapter>().NO_RESULT_DATA();
        }
    }
    @ApiOperation("根据章节id，修改章节")
    @PutMapping("/update")
    public CommonResult<Boolean> updateById(@RequestBody Chapter chapter){
        boolean update = chapterService.updateById(chapter);
        if (update){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR();
        }
    }
    @ApiOperation("根据章节id，删除章节")
    @DeleteMapping("/remove/{chapterId}")
    public CommonResult<Boolean> removeById(@PathVariable("chapterId")String chapterId){
        Boolean result = chapterService.removeChapterById(chapterId);
        if (result){
            return new CommonResult<Boolean>().SUCCESS();
        }else {
            return new CommonResult<Boolean>().ERROR().message("删除失败");
        }
    }

}
