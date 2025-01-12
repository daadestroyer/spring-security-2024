package com.springsecurity.JWTAuthenticationAuthorization.controller;

import com.springsecurity.JWTAuthenticationAuthorization.model.JWTResponse;
import com.springsecurity.JWTAuthenticationAuthorization.model.RefreshToken;
import com.springsecurity.JWTAuthenticationAuthorization.model.RefreshTokenRequest;
import com.springsecurity.JWTAuthenticationAuthorization.model.UserDto;
import com.springsecurity.JWTAuthenticationAuthorization.security.JWTUtility;
import com.springsecurity.JWTAuthenticationAuthorization.service.RefreshTokenService;
import com.springsecurity.JWTAuthenticationAuthorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtility jwtUtility;

    @PostMapping("/registration")
    public String registerUser(@RequestBody UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return this.userService.registerUser(userDto);
    }

    @PostMapping("/refresh-token")
    public JWTResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        return refreshTokenService.findByToken(refreshTokenRequest.getRefreshToken())// get the refresh token
                .map(refreshTokenService::verifyExpiration) // check the refresh token expiration
                .map(RefreshToken::getUserDto) // get the userDto
                .map(userDto -> {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
                    String jwtToken = jwtUtility.generateToken(userDetails);
                    return JWTResponse.builder()
                            .jwtToken(jwtToken)
                            .refreshToken(refreshTokenRequest.getRefreshToken()).userName(userDetails.getUsername())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in db !"));


    }


}
