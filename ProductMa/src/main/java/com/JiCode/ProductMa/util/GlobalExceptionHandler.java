package com.JiCode.ProductMa.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.JiCode.ProductMa.common.ErrorResponse;
import com.JiCode.ProductMa.exception.ServerException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleSelectFailedException(ServerException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setOk(false);
        errorResponse.setMsg(e.getMessage());
        return errorResponse;
    }
}