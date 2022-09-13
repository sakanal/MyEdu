package com.sakanal.edu.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakanal.base.exception.MyException;
import com.sakanal.edu.entity.Subject;
import com.sakanal.edu.entity.excel.SubjectData;
import com.sakanal.edu.service.SubjectService;
import com.sakanal.utils.code.ResultCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubjectListener extends AnalysisEventListener<SubjectData> {
    private SubjectService subjectService;
    public SubjectListener() {}
    public SubjectListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new MyException(ResultCode.NO_RESULT_DATA.getKey(),"文件数据为空");
        }
        Subject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject==null){
            existOneSubject = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
            log.info("存储一级标签"+existOneSubject.getTitle());
        }else {
            log.info(existOneSubject.getTitle()+"已存在一级标签中");
        }
        String oneSubjectId = existOneSubject.getId();
        Subject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(),oneSubjectId);
        if (existTwoSubject==null){
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(oneSubjectId);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
            log.info("存储二级标签"+existTwoSubject.getTitle());
        }else {
            log.info(existTwoSubject.getTitle()+"已存在二级标签中");
        }
    }

    /**
     * 判断一级分类是否重复
     * @param subjectService
     * @param name
     * @return
     */
    private Subject existOneSubject(SubjectService subjectService,String name){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name).eq("parent_id","0");
        return subjectService.getOne(queryWrapper);
    }

    /**
     * 判断二级分类是否重复
     * @param subjectService
     * @param name
     * @return
     */
    private Subject existTwoSubject(SubjectService subjectService,String name,String parentId){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name).eq("parent_id",parentId);
        return subjectService.getOne(queryWrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
