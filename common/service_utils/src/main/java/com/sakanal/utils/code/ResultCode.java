package com.sakanal.utils.code;


public enum ResultCode {
    SUCCESS(200),
    NO_RESULT_DATA(201),
    NOT_FOUND(404),
    MISSING_PARAMETERS(401),
    NO_PARAMETERS(402),
    ERROR(444);

    private final Integer key;

    ResultCode(Integer key) {
        this.key=key;
    }

    public Integer getKey() {
        return this.key;
    }
}
