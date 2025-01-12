package com.springsecurity.JWTAuthenticationAuthorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AuthenticationResponse {
    private String JWTToken;
    private String username;

    public String getJWTToken() {
        return JWTToken;
    }

    public void setJWTToken(String JWTToken) {
        this.JWTToken = JWTToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AuthenticationResponse(String JWTToken, String username) {
        this.JWTToken = JWTToken;
        this.username = username;
    }
}
