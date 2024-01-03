package com.JiCode.ProductMa.adaptor.output.feign.dto;

import com.JiCode.ProductMa.common.CodeEnum;

import lombok.Data;

@Data
public class CommonDto<T> {
    public String msg;
    public CodeEnum code;
    public T data;
}
