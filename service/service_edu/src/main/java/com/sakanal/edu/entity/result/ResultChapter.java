package com.sakanal.edu.entity.result;

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
    private List<ResultChapter> children;

    public ResultChapter(String id, String title, Boolean isParent) {
        this.id = id;
        this.title = title;
        this.isParent = isParent;
    }
}
