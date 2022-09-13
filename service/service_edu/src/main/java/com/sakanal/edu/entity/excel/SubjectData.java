package com.sakanal.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubjectData {
    @ApiModelProperty("表格第一列")
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ApiModelProperty("表格第二列")
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
