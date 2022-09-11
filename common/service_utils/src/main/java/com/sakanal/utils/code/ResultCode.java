package com.sakanal.utils.code;


public enum ResultCode {
    SUCCESS(20000),
    NO_RESULT_DATA(20001),
    NOT_FOUND(40004),
    MISSING_PARAMETERS(40001),
    NO_PARAMETERS(40002),
    ERROR(44444);

    private final Integer key;

    ResultCode(Integer key) {
        this.key=key;
    }

    public Integer getKey() {
        return this.key;
    }
}
