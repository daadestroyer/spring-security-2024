package com.thecoderstv.facebook_group_management.service;

import com.thecoderstv.facebook_group_management.constant.RoleConstant;
import com.thecoderstv.facebook_group_management.entity.CustomUserDetails;
import com.thecoderstv.facebook_group_management.entity.FBUser;
import com.thecoderstv.facebook_group_management.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FBUser user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
        return new CustomUserDetails(user);
    }

    public FBUser joinGroup(FBUser fbUser) {
        // Setting default role as ROLE_USER
        fbUser.setRole(RoleConstant.DEFAULT_ACCESS); // Ensure RoleConstant has a constant ROLE_USER
        FBUser user = this.userRepo.save(fbUser);
        logger.info("New user joined: {}", user);
        return user;
    }

    public String giveAccessToNewelyJoinedUser(int userId, String userRole, Principal principal) {
        // First, check what roles the logged-in user can assign
        List<String> rolesOfLoggedInUser = getRolesByLoggedInUser(principal);

        // Find the user to whom the new role is to be assigned
        FBUser user = this.userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));


        String newRole = "";
        // Check if the logged-in user has the permission to assign the new role
        if (rolesOfLoggedInUser.contains(userRole) ) {
            // Add the new role to the Set of current roles
            newRole = user.getRole() + "," + userRole;
            logger.info("updated role = "+newRole);
            user.setRole(newRole);
            FBUser updatedUser = this.userRepo.save(user);
            logger.info("updated user ="+updatedUser);
        }
        else{
            logger.info("something went wrong...");
        }

        return "Hi " + user.getUsername() + ", new role as an "+userRole+" assigned to you by " + principal.getName();
    }

    private List<String> getRolesByLoggedInUser(Principal principal) {
        FBUser loggedInUser = this.userRepo.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Logged-in user not found"));

        List<String> loggedInUserRoles = Arrays.stream(loggedInUser.getRole().split(","))
                .collect(Collectors.toList());
        logger.info("loggedin user role-->"+loggedInUserRoles);
        if (loggedInUserRoles.contains("ROLE_ADMIN")) {
            logger.info("Logged-in user is an admin");
            return Arrays.asList(RoleConstant.ADMIN_ACCESS);
        } else if (loggedInUserRoles.contains("ROLE_MODERATOR")) {
            logger.info("Logged-in user is a moderator");
            return Arrays.asList(RoleConstant.MODERATOR_ACCESS);
        } else {
            logger.info("Logged-in user has no role access");
            return Collections.emptyList();
        }
    }

    public List<FBUser> getAllUsers() {
        List<FBUser> allUsers = this.userRepo.findAll();
        logger.info("Fetched all users");
        return allUsers;
    }
}