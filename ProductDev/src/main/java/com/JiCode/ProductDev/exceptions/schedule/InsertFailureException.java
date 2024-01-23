package com.JiCode.ProductDev.exceptions.schedule;

public class InsertFailureException extends Exception{
    public InsertFailureException(){
        super();
    }
    public InsertFailureException(String message){
        super(message);
    }
}
