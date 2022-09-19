package com.sakanal.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.sakanal.base.exception.MyException;
import com.sakanal.vod.service.VodService;
import com.sakanal.vod.utils.ConstantPropertiesUtils;
import com.sakanal.vod.utils.InitVodClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile multipartFile){
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = multipartFile.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtils.KEY_ID,
                    ConstantPropertiesUtils.KEY_SECRET,
                    title,
                    fileName,
                    inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
                log.info("VideoId=" + response.getVideoId() + "\n");
                log.info("ErrorCode=" + response.getCode() + "\n");
                log.info("ErrorMessage=" + response.getMessage() + "\n");
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeVideo(String videoSourceId) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            deleteVideoRequest.setVideoIds(videoSourceId);
            client.getAcsResponse(deleteVideoRequest);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }
    }

    @Override
    public void batchRemoveVideo(List<String> videoIdList) {
        String batchVideoSourceId = StringUtils.join(videoIdList, ",");
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);
            DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
            deleteVideoRequest.setVideoIds(batchVideoSourceId);
            client.getAcsResponse(deleteVideoRequest);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"批量删除视频失败");
        }
    }
}
