package com.sakanal.edu.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseQuery {
    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程价格")
    private Integer price;

    @ApiModelProperty(value = "课程状态",example = "Normal or Draft")
    private String status;

    @ApiModelProperty(value = "查询的开始时间",example = "2022-09-10 10:10:01")
    private String startTime;

    @ApiModelProperty(value = "查询的结束时间",example = "2022-09-10 10:10:01")
    private String endTime;
}
