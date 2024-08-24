package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confidential")
public class HomeController {
    @GetMapping
    public String home(){
        return "You have accessed confidential data";
    }
}
