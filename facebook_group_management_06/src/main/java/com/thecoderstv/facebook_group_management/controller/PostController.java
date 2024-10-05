package com.thecoderstv.facebook_group_management.controller;

import com.thecoderstv.facebook_group_management.entity.Post;
import com.thecoderstv.facebook_group_management.entity.PostStatus;
import com.thecoderstv.facebook_group_management.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepo postRepo;

    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(@RequestBody Post post, Principal principal) {
        post.setPostStatus(PostStatus.PENDING);
        post.setUserName(principal.getName());
        this.postRepo.save(post);
        return new ResponseEntity<>(principal.getName() + " your post published successfully, required Admin/Moderator Action", HttpStatus.OK);
    }

    @GetMapping("/approve-post/{postId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> approvePost(@PathVariable int postId) {
        Post post = this.postRepo.findById(postId).get();
        post.setPostStatus(PostStatus.APPROVED);
        Post savedPost = postRepo.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);


    }
    @GetMapping("/approve-all")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<?> approveAllPost() {
        postRepo.findAll().stream().filter(post -> post.getPostStatus().equals(PostStatus.PENDING)).forEach(post -> {
            post.setPostStatus(PostStatus.APPROVED);
            postRepo.save(post);
        });
        return new ResponseEntity<>("Approved all pending post", HttpStatus.OK);
    }
    @GetMapping("/reject-post/{postId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<?> rejectPost(@PathVariable int postId) {
        Post post = postRepo.findById(postId).get();
        post.setPostStatus(PostStatus.REJECTED);
        postRepo.save(post);
        return new ResponseEntity<>("Post " + postId + " rejected ...", HttpStatus.OK);
    }
    @GetMapping("/reject-all")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<?> rejectAllPost() {
        postRepo.findAll().stream().filter(post -> post.getPostStatus().equals(PostStatus.PENDING)).forEach(post -> {
            post.setPostStatus(PostStatus.REJECTED);
            postRepo.save(post);
        });
        return new ResponseEntity<>("Reject all pending post", HttpStatus.OK);
    }
    @GetMapping("/approved-post")
    public ResponseEntity<?> getAllApprovedPost() {
        List<Post> allPost = this.
                postRepo
                .findAll()
                .stream()
                .filter((post) -> post.getPostStatus().equals(PostStatus.APPROVED))
                .collect(Collectors.toList());
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }
    @GetMapping("/pending-post")
    public ResponseEntity<?> pendingPost() {
        List<Post> pendingPost = this.postRepo.findAll().stream().filter((post) -> post.getPostStatus().equals(PostStatus.PENDING)).collect(Collectors.toList());
        return new ResponseEntity<>(pendingPost, HttpStatus.OK);
    }
    @GetMapping("/rejected-post")
    public ResponseEntity<?> rejectedPost() {
        List<Post> rejectedPost = this.postRepo.findAll().stream().filter((post) -> post.getPostStatus().equals(PostStatus.REJECTED)).collect(Collectors.toList());
        return new ResponseEntity<>(rejectedPost, HttpStatus.OK);
    }


}
