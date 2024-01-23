package com.JiCode.ProductDev.exceptions.sprint;

import java.util.Date;

public class DeleteFailureException extends Exception{
    public DeleteFailureException(){
        super();
    }
    public DeleteFailureException(String message){
        super(message);
    }
}
