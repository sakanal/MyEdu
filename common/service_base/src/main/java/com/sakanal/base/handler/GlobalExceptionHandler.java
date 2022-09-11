package com.sakanal.base.handler;

import com.sakanal.base.exception.MyException;
import com.sakanal.utils.entity.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<Boolean> error(Exception e){
        e.printStackTrace();
        return new CommonResult<Boolean>().ERROR().message("执行了全局统一异常处理");
    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public CommonResult<Boolean> error(ArithmeticException e){
        e.printStackTrace();
        return new CommonResult<Boolean>().ERROR().message("执行了ArithmeticException异常处理");
    }
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public CommonResult<Boolean> error(MyException e){
        e.printStackTrace();
        return new CommonResult<Boolean>().ERROR().code(e.getExceptionCode()).message(e.getExceptionMessage());
    }
}
