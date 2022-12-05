package com.sakanal.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideo(MultipartFile multipartFile);

    void removeVideo(String videoSourceId);

    void batchRemoveVideo(List<String> videoIdList);

    String getPlayAuth(String id);
}
