package com.thecoderstv.spring_security_with_mysqldb.Controller;


import com.thecoderstv.spring_security_with_mysqldb.Model.User;
import com.thecoderstv.spring_security_with_mysqldb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PutMapping("/update-role/{userId}/{role}")
    public ResponseEntity<?> updateRole(@PathVariable int userId, @PathVariable String role) {
        System.out.println("Updating role for userId: " + userId + " to role: " + role);
        User user = this.userService.updateRole(userId, role);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/hello")
    public String postAPI() {
        return "accessing admin post api";
    }

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        List<User> allUser = this.userService.findAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        User user = this.userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
