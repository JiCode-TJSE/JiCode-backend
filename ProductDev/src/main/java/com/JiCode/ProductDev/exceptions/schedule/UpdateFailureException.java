package com.JiCode.ProductDev.exceptions.schedule;

public class UpdateFailureException extends Exception{
    public UpdateFailureException(){
        super();
    }
    public UpdateFailureException(String message){
        super(message);
    }
}
