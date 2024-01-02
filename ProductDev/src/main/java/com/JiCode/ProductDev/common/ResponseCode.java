package com.JiCode.ProductDev.common;

/**
 * @author sty
 */
public enum ResponseCode {

    /**
     * response code
     *
     */
    SUCCESS(200,"请求成功"),
    NOT_LOGIN(300,"请登录"),
    NO_PERMISSION(310,"没权限"),
    BAD_REQUEST(400,"参数错误"),
    SYSTEM_ERROR(500,"系统较忙，请稍后再试");

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResponseCode(ResponseCode other){
        this(other.getCode(), other.getMessage());
    }

    public ResponseCode cloneResponse(){
        return this;
    }

    Integer code;
    String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
