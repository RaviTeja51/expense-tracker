package com.personal.expenseapi.exception;

import org.springframework.hateoas.RepresentationModel;

public class UnAuthorisedError extends RepresentationModel<UnAuthorisedError> {
    private String message;
    private String details;
    private String loginLink;
    private String signUpLink;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLoginLink() {
        return loginLink;
    }

    public void setLoginLink(String loginLink) {
        this.loginLink = loginLink;
    }

    public String getSignUpLink() {
        return signUpLink;
    }

    public void setSignUpLink(String signUpLink) {
        this.signUpLink = signUpLink;
    }
}
