package com.JiCode.ProductMa.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CodeEnum {
    SUCCESS(200),
    ERROR(500);
    // 添加更多的代码...

    private final int code;

    CodeEnum(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}