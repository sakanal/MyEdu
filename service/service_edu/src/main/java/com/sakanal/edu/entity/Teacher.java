package com.sakanal.edu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author sakanal
 * @since 2022-09-10
 */
@Data
@TableName("edu_teacher")
@ApiModel(value = "Teacher对象", description = "讲师")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("讲师ID")
    @TableId("id")
    private String id;

    @ApiModelProperty("讲师姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("讲师简介")
    @TableField("intro")
    private String intro;

    @ApiModelProperty("讲师资历,一句话说明讲师")
    @TableField("career")
    private String career;

    @ApiModelProperty("头衔 1高级讲师 2首席讲师 0其他")
    @TableField(value = "level",fill = FieldFill.INSERT)
    private Integer level;

    @ApiModelProperty("讲师头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;


}
