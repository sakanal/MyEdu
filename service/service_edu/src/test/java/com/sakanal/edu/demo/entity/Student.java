package com.sakanal.edu.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @ExcelProperty(value = "学生编号",index = 0)
    private String sNo;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sName;
}
