package com.sakanal.edu.service;

import com.sakanal.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
public interface VideoService extends IService<Video> {
    void removeVideoByCourseId(String courseId);
}
