package com.JiCode.ProductMa.adaptor.input.vo;

import lombok.Data;

@Data
public class CommonVo<T> {
    private String msg;
    private boolean ok;
    private T data;
}