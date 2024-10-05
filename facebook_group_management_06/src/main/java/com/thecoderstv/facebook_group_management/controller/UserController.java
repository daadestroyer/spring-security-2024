package com.thecoderstv.facebook_group_management.controller;

import com.thecoderstv.facebook_group_management.entity.FBUser;
import com.thecoderstv.facebook_group_management.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // join group [accessible to everyone]
    @PostMapping("/join")
    public ResponseEntity<?> joinGroup(@RequestBody FBUser fbUser) {
        fbUser.setPassword(passwordEncoder.encode(fbUser.getPassword()));
        FBUser savedUser = this.customUserDetailsService.joinGroup(fbUser);
        return new ResponseEntity<>("Hi " + fbUser.getUsername() + " welcome to the group", HttpStatus.OK);
    }

    // Assign role/access to new user
    @GetMapping("/access/{userId}/{userRole}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> giveAccessToNewUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
        String message = this.customUserDetailsService.giveAccessToNewelyJoinedUser(userId,userRole,principal);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/findall")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> getAllUser(){
        List<FBUser> userList=  this.customUserDetailsService.getAllUser();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

}
