package com.sakanal.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.base.exception.MyException;
import com.sakanal.edu.entity.Chapter;
import com.sakanal.edu.entity.Video;
import com.sakanal.edu.entity.result.ResultChapter;
import com.sakanal.edu.mapper.ChapterMapper;
import com.sakanal.edu.mapper.VideoMapper;
import com.sakanal.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;
    @Resource
    private VideoMapper videoMapper;

    @Override
    public List<ResultChapter> getAllChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        //根据课程id获取该课程的所有章节
        List<Chapter> chapterList = chapterMapper.selectList(chapterQueryWrapper);

        //存在章节
        if (chapterList.size()>0){
            QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("course_id",courseId);
            //根据课程id获取该课程的所有小节
            List<Video> videoList = videoMapper.selectList(videoQueryWrapper);

            List<ResultChapter> resultChapterList = new ArrayList<>();
            for (Chapter chapter: chapterList){
                //先放入该章节id和title
                ResultChapter resultChapter = new ResultChapter();
                BeanUtils.copyProperties(chapter,resultChapter);
                resultChapter.setIsParent(true);

                //存在小节
                if (videoList.size()>0){
                    String chapterId = chapter.getId();
                    List<ResultChapter> resultVideoList = new ArrayList<>();
                    for (Video video : videoList) {
                        ResultChapter resultVideo = new ResultChapter();
                        if (chapterId.equals(video.getChapterId())) {
                            BeanUtils.copyProperties(video, resultVideo);
                            resultVideo.setIsParent(false);
                            resultVideoList.add(resultVideo);
                        }
                    }
                    //放入小节列表
                    resultChapter.setChildren(resultVideoList);
                }

                //将该章节以及小节放入最终列表中
                resultChapterList.add(resultChapter);
            }
            return resultChapterList;
        }else {
            return null;
        }
    }

    @Override
    public Boolean removeChapterById(String chapterId) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id",chapterId);
        Long count = videoMapper.selectCount(videoQueryWrapper);
        if (count>0){
            throw new MyException(20001,"该章节下存在未删除的小节");
        }else {
            int result = chapterMapper.deleteById(chapterId);
            return result > 0;
        }
    }
}
