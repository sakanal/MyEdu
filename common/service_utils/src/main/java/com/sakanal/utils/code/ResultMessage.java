package com.sakanal.utils.code;

public enum ResultMessage {
    SUCCESS("成功"),
    NO_RESULT_DATA("暂无数据"),
    NOT_FOUND("未找到路径"),
    MISSING_PARAMETERS("缺少参数"),
    NO_PARAMETERS("无参数"),
    ERROR("失败");

    private final String message;

    ResultMessage(String message) {
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
}
