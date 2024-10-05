package com.thecoderstv.facebook_group_management.service;

import com.thecoderstv.facebook_group_management.constant.RoleConstant;
import com.thecoderstv.facebook_group_management.entity.CustomerUserDetails;
import com.thecoderstv.facebook_group_management.entity.FBUser;
import com.thecoderstv.facebook_group_management.repo.UserRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FBUser user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
        return new CustomerUserDetails(user);
    }

    public FBUser joinGroup(FBUser fbUser) {
        // setting default role
        fbUser.setRole(RoleConstant.DEFAULT_ACCESS);
        return this.userRepo.save(fbUser);
    }

    public String giveAccessToNewelyJoinedUser(int userId, String userRole, Principal principal) {
        // SHUBHAM --> ADMIN
        // RAM --> MODERATOR,ADMIN
        // First check what roles the logged-in user can assign
        List<String> rolesOfLoggedInUser = getRolesByLoggedInUser(principal);

        // check the user is exist or not
        FBUser user = this.userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + " not found"));

        String newRole = "";
        if (rolesOfLoggedInUser.contains(userRole)) {
            newRole = user.getRole() + "," + userRole;
            logger.info("updated role = " + newRole);
            user.setRole(newRole);
            FBUser updatedUser = this.userRepo.save(user);
            logger.info("updated user = " + updatedUser);
        } else {
            logger.info("something went wrong....");
        }
        return "Hi " + user.getUsername() + ", new role as an " + userRole + " assigned by " + principal.getName();
    }

    private List<String> getRolesByLoggedInUser(Principal principal) {
        FBUser loggedInUser = this.userRepo.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(" Logged-in user not found"));
        // "ROLE_ADMIN,ROLE_MODERATOR"
        List<String> loggedInUserRoles = Arrays.stream(loggedInUser.getRole().split(",")).collect(Collectors.toList());
        logger.info("loggedin user role-->" + loggedInUserRoles);

        if (loggedInUserRoles.contains("ROLE_ADMIN")) {
            logger.info("Logged-in user is an admin");
            return Arrays.asList(RoleConstant.ADMIN_ACCESS);
        } else if (loggedInUserRoles.contains("ROLE_MODERATOR")) {
            logger.info("Logged-in user is an moderator");
            return Arrays.asList(RoleConstant.MODERATOR_ACCESS);
        } else {
            logger.info("Logged-in user has no role to access");
            return Collections.emptyList();
        }
    }

    public List<FBUser> getAllUser() {
        return this.userRepo.findAll();
    }
}
