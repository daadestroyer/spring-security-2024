package com.thecoderstv.facebook_group_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private int postId;
    private String subject;
    private String description;
    private String userName;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;
}
