package com.springsecurity.JWTAuthenticationAuthorization.repo;


import com.springsecurity.JWTAuthenticationAuthorization.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
    Optional<UserDto> findByUsername(String username);
}
