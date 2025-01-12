package com.springsecurity.JWTAuthenticationAuthorization.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class JWTResponse {
    private String jwtToken;
    private String refreshToken;

    private String userName;


}
