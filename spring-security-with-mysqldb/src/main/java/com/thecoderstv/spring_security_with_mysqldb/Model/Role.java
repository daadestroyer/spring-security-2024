package com.thecoderstv.spring_security_with_mysqldb.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
