package com.thecoderstv.spring_security_with_mysqldb.Repo;

import com.thecoderstv.spring_security_with_mysqldb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByuserNameOrEmail(String username, String email);
}
