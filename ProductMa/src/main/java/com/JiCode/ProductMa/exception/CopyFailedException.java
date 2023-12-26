package com.JiCode.ProductMa.exception;

public class CopyFailedException extends Exception {

    public CopyFailedException() {
        super();
    }

    public CopyFailedException(String message) {
        super(message);
    }

    public CopyFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CopyFailedException(Throwable cause) {
        super(cause);
    }

}