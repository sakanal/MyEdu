package com.sakanal.utils.entity;

import com.sakanal.utils.code.ResultCode;
import com.sakanal.utils.code.ResultMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private Boolean success;
    private String message;
    private T data;

    public CommonResult(Integer code, Boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public CommonResult<T> SUCCESS(T data){
        this.code = ResultCode.SUCCESS.getKey();
        this.success = true;
        this.message = ResultMessage.SUCCESS.getMessage();
        this.data=data;
        return this;
    }
    public CommonResult<T> SUCCESS(){
        this.code = ResultCode.SUCCESS.getKey();
        this.success = true;
        this.message = ResultMessage.SUCCESS.getMessage();
        return this;
    }
    public CommonResult<T> NO_RESULT_DATA(){
        this.code = ResultCode.NO_RESULT_DATA.getKey();
        this.success = false;
        this.message = ResultMessage.NO_RESULT_DATA.getMessage();
        return this;
    }

    public CommonResult<T> NOT_FOUND(){
        this.code = ResultCode.NOT_FOUND.getKey();
        this.success = false;
        this.message = ResultMessage.NOT_FOUND.getMessage();
        return this;
    }
    public CommonResult<T> MISSING_PARAMETERS(){
        this.code = ResultCode.MISSING_PARAMETERS.getKey();
        this.success = false;
        this.message = ResultMessage.MISSING_PARAMETERS.getMessage();
        return this;
    }
    public CommonResult<T> NO_PARAMETERS(){
        this.code = ResultCode.NO_PARAMETERS.getKey();
        this.success = false;
        this.message = ResultMessage.NO_PARAMETERS.getMessage();
        return this;
    }
    public CommonResult<T> ERROR(){
        this.code = ResultCode.ERROR.getKey();
        this.success = false;
        this.message = ResultMessage.ERROR.getMessage();
        return this;
    }



    public CommonResult<T> message(String message){
        this.message=message;
        return this;
    }
    public CommonResult<T> code(Integer code){
        this.code=code;
        return this;
    }



}
