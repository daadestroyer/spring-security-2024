package com.thecoderstv.spring_security_with_mysqldb.Controller;

import com.thecoderstv.spring_security_with_mysqldb.Model.User;
import com.thecoderstv.spring_security_with_mysqldb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
