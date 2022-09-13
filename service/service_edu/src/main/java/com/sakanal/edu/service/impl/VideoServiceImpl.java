package com.sakanal.edu.service.impl;

import com.sakanal.edu.entity.Video;
import com.sakanal.edu.mapper.VideoMapper;
import com.sakanal.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
