package com.personal.expenseapi.exception;

import org.springframework.hateoas.RepresentationModel;


public class UnAuthorizedAccessException extends BaseException{

    public UnAuthorizedAccessException(String message, String details, String code) {
        super(message, details, code);
    }
}
