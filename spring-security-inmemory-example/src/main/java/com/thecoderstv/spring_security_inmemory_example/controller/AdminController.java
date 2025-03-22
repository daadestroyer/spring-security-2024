package com.thecoderstv.spring_security_inmemory_example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/admin")
public class AdminController {

    
    @GetMapping
    public String api1(){
        return "admin api";
    }
}
