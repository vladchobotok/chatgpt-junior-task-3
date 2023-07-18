package com.example.juniortask3.controller;

import com.example.juniortask3.entity.Post;
import com.example.juniortask3.entity.User;
import com.example.juniortask3.repository.PostRepository;
import com.example.juniortask3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.juniortask3.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SocialMediaController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public SocialMediaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Create a new user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new post
    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    // Get all posts
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Add a user as a follower of another user
    @PostMapping("/users/{followerId}/follow/{followingId}")
    public User followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        User follower = userRepository.findById(followerId).orElseThrow(ResourceNotFoundException::new);
        User following = userRepository.findById(followingId).orElseThrow(ResourceNotFoundException::new);

        follower.getFollowing().add(following);
        following.getFollowers().add(follower);

        userRepository.save(follower);
        userRepository.save(following);

        return follower;
    }


    // Like a post
    @PostMapping("/users/{userId}/like/{postId}")
    public User likePost(@PathVariable Long userId, @PathVariable Long postId) {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        post.getLikes().add(user);
        postRepository.save(post);
        return user;
    }
}

