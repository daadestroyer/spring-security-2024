package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Value("${spring.security.user.name}")
    private String username;
    @GetMapping("/dashboard")
    public String dashboard(){
        return "Welcome back "+username;
    }

}
