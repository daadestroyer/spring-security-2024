package com.springsecurity.JWTAuthenticationAuthorization.controller;

import com.springsecurity.JWTAuthenticationAuthorization.config.JWTAuthenticationFilter;
import com.springsecurity.JWTAuthenticationAuthorization.model.AuthenticationRequest;
import com.springsecurity.JWTAuthenticationAuthorization.model.AuthenticationResponse;
import com.springsecurity.JWTAuthenticationAuthorization.model.JWTResponse;
import com.springsecurity.JWTAuthenticationAuthorization.model.RefreshToken;
import com.springsecurity.JWTAuthenticationAuthorization.security.JWTUtility;
import com.springsecurity.JWTAuthenticationAuthorization.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private RefreshTokenService refreshTokenService;
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println(authenticationRequest);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authenticationRequest.getUsername());
        final String jwtToken = jwtUtility.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(jwtToken,refreshToken.getToken(),userDetails.getUsername()));

    }

}