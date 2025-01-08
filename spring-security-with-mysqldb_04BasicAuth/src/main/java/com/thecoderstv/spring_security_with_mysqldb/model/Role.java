package com.thecoderstv.spring_security_with_mysqldb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Role {
    @Id
    @GeneratedValue
    private int roleId;
    private String role;
}