package com.JiCode.ProductMa.exception;

public class SelectFailedException extends Exception {

    public SelectFailedException() {
        super();
    }

    public SelectFailedException(String message) {
        super(message);
    }

    public SelectFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectFailedException(Throwable cause) {
        super(cause);
    }

}