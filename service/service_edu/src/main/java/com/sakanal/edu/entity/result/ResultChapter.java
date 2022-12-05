package com.sakanal.edu.entity.result;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultChapter {
    private String id;
    private String title;
    private Boolean isParent;

    @ApiModelProperty("是否可以试听：0收费 1免费")
    private Integer isFree;

    @ApiModelProperty("云端视频资源")
    private String videoSourceId;

    private List<ResultChapter> children;

    public ResultChapter(String id, String title, Boolean isParent) {
        this.id = id;
        this.title = title;
        this.isParent = isParent;
    }
}
