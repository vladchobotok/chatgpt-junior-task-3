package com.example.juniortask3.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<User> following;

    @ManyToMany(mappedBy = "following", fetch = FetchType.LAZY)
    private List<User> followers;

    public List<User> getFollowers() {
        return followers;
    }

    public User() {
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<User> getFollowing() {
        return following;
    }

    public User(String username) {
        this.username = username;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }
}
