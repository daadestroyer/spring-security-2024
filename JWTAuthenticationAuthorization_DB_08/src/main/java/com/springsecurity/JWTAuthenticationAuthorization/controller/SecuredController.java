package com.springsecurity.JWTAuthenticationAuthorization.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {
    @GetMapping("/v1")
    @Secured("ROLE_ADMIN")
    public String api1(){
        return "you have accessed admin api";
    }

    @GetMapping("/v2")
    @Secured("ROLE_USER")
    public String api2(){
        return "you have accessed user api";
    }

}
