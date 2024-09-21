package com.thecoderstv.facebook_group_management.repo;

import com.thecoderstv.facebook_group_management.entity.Post;
import com.thecoderstv.facebook_group_management.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
}
