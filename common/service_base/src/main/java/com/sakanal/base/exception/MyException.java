package com.sakanal.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException{
    private Integer exceptionCode;
    private String exceptionMessage;
}
