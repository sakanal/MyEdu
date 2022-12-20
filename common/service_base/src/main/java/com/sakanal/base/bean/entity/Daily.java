package com.sakanal.base.bean.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 网站统计日数据
 * </p>
 *
 * @author sakanal
 * @since 2022-12-19
 */
@Getter
@Setter
@TableName("statistics_daily")
@ApiModel(value = "Daily对象", description = "网站统计日数据")
public class Daily implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
      @TableId("id")
    private String id;

    @ApiModelProperty("统计日期")
    @TableField("date_calculated")
    private String dateCalculated;

    @ApiModelProperty("注册人数")
    @TableField("register_num")
    private Integer registerNum;

    @ApiModelProperty("登录人数")
    @TableField("login_num")
    private Integer loginNum;

    @ApiModelProperty("每日播放视频数")
    @TableField("video_view_num")
    private Integer videoViewNum;

    @ApiModelProperty("每日新增课程数")
    @TableField("course_num")
    private Integer courseNum;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
