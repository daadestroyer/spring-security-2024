package com.thecoderstv.spring_security_db_example.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;  // Example: ROLE_USER, ROLE_ADMIN
}
