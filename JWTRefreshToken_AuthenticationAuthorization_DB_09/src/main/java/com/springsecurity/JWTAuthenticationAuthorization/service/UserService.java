package com.springsecurity.JWTAuthenticationAuthorization.service;

import com.springsecurity.JWTAuthenticationAuthorization.model.UserDto;
import com.springsecurity.JWTAuthenticationAuthorization.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String registerUser(UserDto userDto){
        this.userRepository.save(userDto);
        return "User Registration Done";
    }
}
