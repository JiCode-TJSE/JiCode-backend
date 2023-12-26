package com.JiCode.ProductMa.exception;

public class ServerException extends Exception {

    public ServerException() {
        super("Server Error");
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

}