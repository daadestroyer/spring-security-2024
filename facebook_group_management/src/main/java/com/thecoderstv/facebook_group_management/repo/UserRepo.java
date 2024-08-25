package com.thecoderstv.facebook_group_management.repo;

import com.thecoderstv.facebook_group_management.entity.FBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<FBUser,Integer> {
    Optional<FBUser> findByUsername(String username);
}
