package com.sakanal.edu.service;

import com.sakanal.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sakanal.edu.entity.result.ResultChapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
public interface ChapterService extends IService<Chapter> {

    List<ResultChapter> getAllChapterByCourseId(String courseId);

    Boolean removeChapterById(String chapterId);
}
