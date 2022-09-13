package com.sakanal.edu.demo;

import com.alibaba.excel.EasyExcel;
import com.sakanal.edu.demo.entity.Student;
import com.sakanal.edu.demo.listener.ExcelListener;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
//        String fileName="D:/write.xlsx";
//        List<Student> studentList = getStudentList();
//        System.out.println(studentList.size());
//        EasyExcel.write(fileName, Student.class).sheet("学生列表").doWrite(studentList);

        String fileName="D:/write.xlsx";
        EasyExcel.read(fileName,Student.class,new ExcelListener()).sheet().doRead();
    }
    public static List<Student> getStudentList(){
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            students.add(new Student(i+"","sakanal"+i));
        }
        return students;
    }
}
