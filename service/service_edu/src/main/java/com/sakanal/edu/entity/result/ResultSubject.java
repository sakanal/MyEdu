package com.sakanal.edu.entity.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultSubject {
    private String id;
    private String title;
    private List<ResultSubject> children;

    public ResultSubject(String id, String title) {
        this.id = id;
        this.title = title;
    }

}
