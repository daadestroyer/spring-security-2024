package com.springsecurity.JWTAuthenticationAuthorization.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@ToString
public class UserDto {
    @Id
    public Long id;
    public String username;
    public String password;
    public String role;


}