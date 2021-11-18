package com.u238.training.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLandingPage(){
        return "landing";
    }

    @GetMapping("/login")
    public String showLoginPage(){

        return "fancy_login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage(){

        return "access_error";
    }

}
