package com.thecoderstv.facebook_group_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class FBUser {
    @Id
    @GeneratedValue
    private int userId;
    private String username;
    private String password;
    private boolean active;
    private String role; // ROLE_ADMIN,ROLE_MODERATOR,ROLE_USER
}
