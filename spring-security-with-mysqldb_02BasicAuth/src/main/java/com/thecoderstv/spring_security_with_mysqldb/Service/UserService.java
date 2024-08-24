package com.thecoderstv.spring_security_with_mysqldb.Service;


import com.thecoderstv.spring_security_with_mysqldb.Model.Role;
import com.thecoderstv.spring_security_with_mysqldb.Model.User;
import com.thecoderstv.spring_security_with_mysqldb.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return this.userRepo.save(user);
    }

    public User updateRole(int userId, String role) {
        System.out.println("Updating role for userId: " + userId + " to role: " + role);
        User user = this.userRepo.findById(userId).get();
        Role updatedRole = Role.builder().role(role).build();
        user.getRoles().add(updatedRole);
        return this.userRepo.save(user);
    }

    public List<User> findAllUser() {
        return this.userRepo.findAll();
    }

    public User findUserById(int userId) {
        return this.userRepo.findById(userId).get();
    }
}
