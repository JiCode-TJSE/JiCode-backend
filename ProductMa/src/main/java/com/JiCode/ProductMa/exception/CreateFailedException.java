package com.JiCode.ProductMa.exception;

public class CreateFailedException extends Exception {

    public CreateFailedException() {
        super();
    }

    public CreateFailedException(String message) {
        super(message);
    }

    public CreateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateFailedException(Throwable cause) {
        super(cause);
    }

}