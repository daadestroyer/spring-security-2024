package com.springsecurity.JWTAuthenticationAuthorization.service;

import com.springsecurity.JWTAuthenticationAuthorization.model.RefreshToken;
import com.springsecurity.JWTAuthenticationAuthorization.model.UserDto;
import com.springsecurity.JWTAuthenticationAuthorization.repo.RefreshTokenRepo;
import com.springsecurity.JWTAuthenticationAuthorization.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String username) {
        UserDto userDto = userRepository.findByUsername(username).get();


        RefreshToken refreshToken = RefreshToken
                .builder()
                .userDto(userDto)
                .token(UUID.randomUUID().toString()).expiryDate(Instant.now().plusMillis(600000)).build();// 10 min
        return refreshTokenRepo.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(refreshToken);
            throw new RuntimeException(refreshToken.getToken() + " refresh token has expired, please make a new signin request");
        }

        return refreshToken;
    }
}
