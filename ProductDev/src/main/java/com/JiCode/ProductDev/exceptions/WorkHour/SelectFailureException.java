package com.JiCode.ProductDev.exceptions.WorkHour;

public class SelectFailureException extends Exception{
    public SelectFailureException(){
        super();
    }
    public SelectFailureException(String message){
        super(message);
    }
}
