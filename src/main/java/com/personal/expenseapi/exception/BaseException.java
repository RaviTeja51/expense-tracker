package com.personal.expenseapi.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({"data","message","details"})
public class BaseException extends Exception{

    private Date date;
    private String details;
    private String message;
    private String code;

    public BaseException(String message,String details,String  code){
        super(message);
        this.message=message;
        this.details=details;
        this.date = new Date(System.currentTimeMillis());
        this.code  = code;
    }

    public BaseException();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
