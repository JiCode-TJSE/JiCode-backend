package com.JiCode.ProductDev.exceptions.backlogitem;

public class DuplicateBacklogitemException extends Exception{
    public DuplicateBacklogitemException(){
        super();
    }
    public DuplicateBacklogitemException(String message){
        super(message);
    }
}
