package com.sakanal.edu.service;

import com.sakanal.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sakanal.edu.entity.result.ResultSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
public interface SubjectService extends IService<Subject> {

    void saveSubject(MultipartFile multipartFile);

    List<ResultSubject> getAllSubject();
}
