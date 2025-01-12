package com.springsecurity.JWTAuthenticationAuthorization.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RefreshTokenRequest {
    private String refreshToken;
}
