package com.JiCode.ProductMa.adaptor.input.vo;

import com.JiCode.ProductMa.common.CodeEnum;

import lombok.Data;

@Data
public class CommonVo<T> {
    public String msg;
    public CodeEnum code;
    public T data;

    public static <T> CommonVo<T> create(String msg, CodeEnum code, T data) {
        CommonVo<T> commonVo = new CommonVo<>();
        commonVo.setMsg(msg);
        commonVo.setCode(code);
        commonVo.setData(data);
        return commonVo;
    }

    public static CommonVo<Void> create(String msg, CodeEnum code) {
        CommonVo<Void> commonVo = new CommonVo<>();
        commonVo.setMsg(msg);
        commonVo.setCode(code);
        return commonVo;
    }
}