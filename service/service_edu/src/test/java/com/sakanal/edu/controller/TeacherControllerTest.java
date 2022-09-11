package com.sakanal.edu.controller;

import com.sakanal.edu.entity.Teacher;
import com.sakanal.utils.entity.CommonResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
class TeacherControllerTest {

    @Resource
    private TeacherController teacherController;
    @Test
    void testGetAll(){
        CommonResult<List<Teacher>> all = teacherController.findAll();
        if (all.getSuccess()){
            List<Teacher> date = all.getData();
            for (Teacher teacher:date){
                System.out.println(teacher);
            }
        }
        System.out.println(all.getCode());
        System.out.println(all.getSuccess());
        System.out.println(all.getMessage());

    }
}
