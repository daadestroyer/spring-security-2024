package com.thecoderstv.facebook_group_management.entity;

import jakarta.persistence.*;
import jdk.jfr.StackTrace;
import lombok.*;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
