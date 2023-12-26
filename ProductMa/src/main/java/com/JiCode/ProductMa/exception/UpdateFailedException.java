package com.JiCode.ProductMa.exception;

public class UpdateFailedException extends Exception {

    public UpdateFailedException() {
        super();
    }

    public UpdateFailedException(String message) {
        super(message);
    }

    public UpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateFailedException(Throwable cause) {
        super(cause);
    }

}