package com.personal.expenseapi.handler;

import com.personal.expenseapi.controller.SignUpController;
import com.personal.expenseapi.exception.BadRequestException;
import com.personal.expenseapi.exception.UnAuthorisedError;
import com.personal.expenseapi.exception.UnAuthorizedAccessException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExpenseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException exception,
            WebRequest  request){
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public  ResponseEntity<?> handleUnAuthorisedError(UnAuthorizedAccessException exception,
            WebRequest request){
        UnAuthorisedError unAuthorisedError = new UnAuthorisedError();
        unAuthorisedError.setMessage(exception.getMessage());
        unAuthorisedError.setDetails(exception.getDetails());

        //Unauthorised access, suggest navigation to login or sing up

        Link loginLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SignUpController.class).login()).withRel("loginLink");
        unAuthorisedError.add(loginLink);

        Link signUpLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SignUpController.class).signUp()).withRel("signUpLink");
        unAuthorisedError.add(signUpLink);


        return  new ResponseEntity<>(unAuthorisedError,HttpStatus.UNAUTHORIZED);
    }
}
