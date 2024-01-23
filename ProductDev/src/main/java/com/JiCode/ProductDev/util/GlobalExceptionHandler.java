package com.JiCode.ProductDev.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author sty
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * validation exceptions
     *
     * @param e exception catched
     *
     * @return data back to fore-end
     */
    //@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public ComResponse handleParameterVerificationException(HttpServletResponse response, Exception e) {
        log.error(" handleParameterVerificationException has been invoked", e);
        String msg = null;
        /// BindException
        if (e instanceof BindException) {
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = ((BindException) e).getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
            /// MethodArgumentNotValidException
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
            /// ValidationException 的子类异常ConstraintViolationException
        }else {
            msg = "处理参数时异常";
        }
        return ComResponse.invalid( msg );
    }

    // @ExceptionHandler(value = {NotLoginException.class})
    // public ComResponse handleAuthException(Exception e) {
    //     log.error(" NotLoginException has been invoked", e.getMessage() );
    //     if (e instanceof NotLoginException) {
    //         return ComResponse.authFaild( e.getMessage() );
    //     }
    //     return ComResponse.authFaild();
    // }

    @ExceptionHandler
    public ComResponse globalException(HttpServletResponse response, Exception ex) {
        log.error("GlobalExceptionHandler..." + response.getStatus(), ex);
        if (ex instanceof NumberFormatException) {
            return ComResponse.error( ex.getMessage() );
        }else if (ex instanceof HttpMessageNotReadableException) {
            return ComResponse.error("字段类型异常");
        }
        return ComResponse.error();
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public ComResponse numberFormatException(NumberFormatException e) {
        log.error("GlobalExceptionHandler...", e);
        if (e instanceof NumberFormatException) {
            return ComResponse.error( e.getMessage() );
        }
        return ComResponse.error();
    }


}

