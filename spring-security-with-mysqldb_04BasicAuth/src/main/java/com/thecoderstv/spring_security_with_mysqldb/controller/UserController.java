package com.thecoderstv.spring_security_with_mysqldb.controller;

import com.thecoderstv.spring_security_with_mysqldb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // http://localhost:8080/user
    @GetMapping
    public String userSecuredAPI(){
        return "User Secured API";
    }

}
