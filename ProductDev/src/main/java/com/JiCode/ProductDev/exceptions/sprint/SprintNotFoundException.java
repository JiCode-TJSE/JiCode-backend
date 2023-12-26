package com.JiCode.ProductDev.exceptions.sprint;

import com.JiCode.ProductDev.domain.model.SprintAggregation;

public class SprintNotFoundException extends Exception{
    public SprintNotFoundException(){
        super();
    }

    public SprintNotFoundException(String message){
        super(message);
    }

    public SprintNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public SprintNotFoundException(Throwable cause){
        super(cause);
    }
}
