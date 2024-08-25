package com.thecoderstv.urlbased_and_rolebased_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    // public/home
    @GetMapping("/home")
    public String home(){
        return "This is home page";
    }
    // /public/contact
    @GetMapping("/contact")
    public String contact(){
        return "This is contact page";
    }
}
