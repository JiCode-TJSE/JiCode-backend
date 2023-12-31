package com.JiCode.ProductDev.exceptions.schedule;

public class DeleteFailureException extends Exception{
    public DeleteFailureException(){
        super();
    }
    public DeleteFailureException(String message){
        super(message);
    }
}
