package com.JiCode.ProductMa.exception;

public class InsertFailedException extends Exception {

    public InsertFailedException() {
        super();
    }

    public InsertFailedException(String message) {
        super(message);
    }

    public InsertFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertFailedException(Throwable cause) {
        super(cause);
    }

}