package com.example.juniortask3.repository;

import com.example.juniortask3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Custom methods if needed
}

