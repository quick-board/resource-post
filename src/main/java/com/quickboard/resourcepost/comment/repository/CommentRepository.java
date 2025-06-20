package com.quickboard.resourcepost.comment.repository;

import com.quickboard.resourcepost.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
