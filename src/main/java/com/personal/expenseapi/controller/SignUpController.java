package com.personal.expenseapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignUpController {

    @PostMapping("/sign-up")
    public String  signUp(){

        return "";
    }

    @PostMapping("/login")
    public String login(){

        return "";
    }



}
