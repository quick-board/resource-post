package com.quickboard.resourcepost.post.repository;

import com.quickboard.resourcepost.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
