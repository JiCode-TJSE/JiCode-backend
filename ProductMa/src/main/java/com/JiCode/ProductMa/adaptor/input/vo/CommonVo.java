package com.JiCode.ProductMa.adaptor.input.vo;

import lombok.Data;

@Data
public class CommonVo<T> {
    public String msg;
    public boolean ok;
    public T data;

    public static <T> CommonVo<T> create(String msg, boolean ok, T data) {
        CommonVo<T> commonVo = new CommonVo<>();
        commonVo.setMsg(msg);
        commonVo.setOk(ok);
        commonVo.setData(data);
        return commonVo;
    }

    public static CommonVo<Void> create(String msg, boolean ok) {
        CommonVo<Void> commonVo = new CommonVo<>();
        commonVo.setMsg(msg);
        commonVo.setOk(ok);
        return commonVo;
    }
}