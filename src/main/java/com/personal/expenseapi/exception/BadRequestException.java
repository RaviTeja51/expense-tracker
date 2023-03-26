package com.personal.expenseapi.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException{

    public BadRequestException(String message,String details,String code){
        super(message,details,code);
    }

    public BadRequestException(){
        super("BAD REQUEST","Invalid request payload", "500");
    }
}
