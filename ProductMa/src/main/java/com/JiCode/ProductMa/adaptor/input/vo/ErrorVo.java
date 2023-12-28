package com.JiCode.ProductMa.adaptor.input.vo;

import com.JiCode.ProductMa.common.CodeEnum;

import lombok.Data;

@Data
public class ErrorVo {
    private CodeEnum code;
    private String msg;
}
