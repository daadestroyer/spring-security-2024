package com.thecoderstv.spring_security_db_example.repo;

import com.thecoderstv.spring_security_db_example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
