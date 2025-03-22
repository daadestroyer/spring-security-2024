package com.thecoderstv.spring_security_inmemory_example.controller;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping
    public String api(){
        return "public api accessed by ";
    }
    
}
