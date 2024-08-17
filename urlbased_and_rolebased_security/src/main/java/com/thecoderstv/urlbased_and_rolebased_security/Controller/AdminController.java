package com.thecoderstv.urlbased_and_rolebased_security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome to admin dashboard ";
    }
}
