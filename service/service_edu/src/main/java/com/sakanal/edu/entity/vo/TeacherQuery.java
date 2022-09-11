package com.sakanal.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔，1-高级讲师 2-首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询的开始时间",example = "2022-09-10 10:10:01")
    private String startTime;

    @ApiModelProperty(value = "查询的结束时间",example = "2022-09-10 10:10:01")
    private String endTime;
}
