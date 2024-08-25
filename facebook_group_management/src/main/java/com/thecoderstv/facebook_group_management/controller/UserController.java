package com.thecoderstv.facebook_group_management.controller;

import com.thecoderstv.facebook_group_management.entity.FBUser;
import com.thecoderstv.facebook_group_management.repo.UserRepo;
import com.thecoderstv.facebook_group_management.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    // Join Group [accessible to admin and moderator]
    // http://localhost:8080/user/join
    @PostMapping("/join")
    public ResponseEntity<?> joinGroup(@RequestBody FBUser fbUser) {
        fbUser.setPassword(passwordEncoder.encode(fbUser.getPassword()));
        System.out.println(fbUser);
        FBUser user = this.customUserDetailsService.joinGroup(fbUser);
        return new ResponseEntity<>("Hi " + fbUser.getUsername() + " welcome to the group", HttpStatus.OK);
    }

    // Assign role/access to new user
    @GetMapping("/access/{userId}/{userRole}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<?> giveAccessToNewUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
        String message = this.customUserDetailsService.giveAccessToNewelyJoinedUser(userId, userRole, principal);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testUserAccess() {
        return new ResponseEntity<>("Accessing secured api", HttpStatus.OK);
    }
    @GetMapping("/findall")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUser() {
        List<FBUser> allUser = this.customUserDetailsService.getAllUsers();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }


}
