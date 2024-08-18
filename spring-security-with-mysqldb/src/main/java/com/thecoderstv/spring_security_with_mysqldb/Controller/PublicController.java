package com.thecoderstv.spring_security_with_mysqldb.Controller;

import com.thecoderstv.spring_security_with_mysqldb.Model.User;
import com.thecoderstv.spring_security_with_mysqldb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String helloGet(){
        return "hello";
    }
    @PostMapping
    public String helloPost(){
        return "hello";
    }
    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User savedUser = this.userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
