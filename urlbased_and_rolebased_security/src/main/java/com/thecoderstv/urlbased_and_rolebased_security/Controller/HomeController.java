package com.thecoderstv.urlbased_and_rolebased_security.Controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "Welcome to home page ";
    }
}
