package com.sakanal.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFileAvatar(String fileDir,MultipartFile multipartFile);
}
