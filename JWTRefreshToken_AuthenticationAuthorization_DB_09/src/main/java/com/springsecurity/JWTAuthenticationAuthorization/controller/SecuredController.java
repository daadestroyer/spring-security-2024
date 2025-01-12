package com.springsecurity.JWTAuthenticationAuthorization.controller;

import com.springsecurity.JWTAuthenticationAuthorization.model.JWTResponse;
import com.springsecurity.JWTAuthenticationAuthorization.model.RefreshToken;
import com.springsecurity.JWTAuthenticationAuthorization.model.RefreshTokenRequest;
import com.springsecurity.JWTAuthenticationAuthorization.security.JWTUtility;
import com.springsecurity.JWTAuthenticationAuthorization.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secured")
public class SecuredController {
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/v1")
    @Secured("ROLE_ADMIN")
    public String api1() {
        return "you have accessed admin api";
    }

    @GetMapping("/v2")
    @Secured("ROLE_USER")
    public String api2() {
        return "you have accessed user api";
    }


}
