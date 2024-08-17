package com.thecoderstv.urlbased_and_rolebased_security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "Welcome to user dashboard ";
    }
}