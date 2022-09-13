package com.sakanal.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.sakanal.edu.Listener.SubjectListener;
import com.sakanal.edu.entity.Subject;
import com.sakanal.edu.entity.excel.SubjectData;
import com.sakanal.edu.mapper.SubjectMapper;
import com.sakanal.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author sakanal
 * @since 2022-09-13
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void saveSubject(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream,SubjectData.class,new SubjectListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
