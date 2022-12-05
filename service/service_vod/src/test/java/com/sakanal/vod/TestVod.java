package com.sakanal.vod;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

public class TestVod {

    public static void main(String[] args) throws ClientException {
//        testUploadVideo("",
//                "",
//                "测试代码方式上传",
//                "D:/guli_edu_api – pom.xml (guli_edu_api).mp4");
        getPlayAuth();
    }
    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
    public static void getPlayAuth() throws ClientException{
            //根据视频id获取视频播放凭证
            //创建初始化对象
            DefaultAcsClient client = InitObject.initVodClient("LTAI5tNkFVyZa2BP7TSyF7tn", "Uq1wTmlvj2CKPIoKADrhyS6txdf7D4");
            //创建获取视频地址request和response
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse  response = new GetVideoPlayAuthResponse();
            //向request对象设置视频id
            request.setVideoId("30b5bac8b80049a59fd4a5bc2986ef74");
            //调用初始化对象的方法，传递request，获取数据
            response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            System.out.print("PlayAuth = " + playAuth + "\n");
    }
    public static void getPlayURL() throws ClientException {
        //根据视频id获取视频播放地址
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tNkFVyZa2BP7TSyF7tn", "Uq1wTmlvj2CKPIoKADrhyS6txdf7D4");
        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象设置视频id
        request.setVideoId("451048539a954dc58730fb1b622b3715");
        //调用初始化对象的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
