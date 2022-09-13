package com.sakanal.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.edu.Listener.SubjectListener;
import com.sakanal.edu.entity.Subject;
import com.sakanal.edu.entity.excel.SubjectData;
import com.sakanal.edu.entity.result.ResultSubject;
import com.sakanal.edu.mapper.SubjectMapper;
import com.sakanal.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public void saveSubject(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream,SubjectData.class,new SubjectListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ResultSubject> getAllSubject() {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id","0");
        //获取所有一级课程
        List<Subject> parentSubject = subjectMapper.selectList(queryWrapper);
        if (parentSubject.size()>0){
            List<ResultSubject> resultSubjectList = new ArrayList<>();
            for (Subject subject:parentSubject){
                //将一级课程的id和title放入返回结果中
                ResultSubject resultSubject = new ResultSubject();
                BeanUtils.copyProperties(subject,resultSubject);
//                resultSubject.setId(subject.getId());
//                resultSubject.setTitle(subject.getTitle());

                String parentId = subject.getId();
                QueryWrapper<Subject> wrapper = new QueryWrapper<>();
                wrapper.eq("parent_id",parentId);
                //根据一级课程的id获取其下属的所有二级课程
                List<Subject> childrenSubjectList = subjectMapper.selectList(wrapper);
                if (childrenSubjectList.size()>0){
                    List<ResultSubject> childrenResultSubjectList = new ArrayList<>();
                    //获取所有二级课程的基本信息
                    for (Subject childrenSubject:childrenSubjectList){
                        childrenResultSubjectList.add(new ResultSubject(childrenSubject.getId(),childrenSubject.getTitle()));
                    }
                    //将二级课程的基本信息放入返回结果中
                    resultSubject.setChildren(childrenResultSubjectList);
                }
                resultSubjectList.add(resultSubject);
            }
            return resultSubjectList;
        }else {
            return null;
        }
    }
}
