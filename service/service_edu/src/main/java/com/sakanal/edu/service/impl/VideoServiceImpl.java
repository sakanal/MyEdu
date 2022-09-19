package com.sakanal.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.edu.entity.Video;
import com.sakanal.edu.feign.VodClient;
import com.sakanal.edu.mapper.VideoMapper;
import com.sakanal.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Resource
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {
        //根据课程id查询所有视频id
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<Video>().eq("course_id", courseId);
        videoQueryWrapper.select("video_source_id");
        List<Video> videoList = this.list(videoQueryWrapper);
        if (videoList.size()>0){
            List<String> VideoSourceIdList = new ArrayList<>();
            for (Video video:videoList){
                if (StringUtils.hasText(video.getVideoSourceId())){
                    VideoSourceIdList.add(video.getVideoSourceId());
                }
            }
            if (VideoSourceIdList.size()>0){
                vodClient.batchRemoveVideo(VideoSourceIdList);
            }
        }

        videoQueryWrapper = new QueryWrapper<Video>().eq("course_id", courseId);
        this.remove(videoQueryWrapper);
    }
}
