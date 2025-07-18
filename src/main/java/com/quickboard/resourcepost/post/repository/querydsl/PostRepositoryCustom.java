package com.quickboard.resourcepost.post.repository.querydsl;

import com.quickboard.resourcepost.post.dto.PostResponse;
import com.quickboard.resourcepost.post.dto.PostSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepositoryCustom {

    Page<PostResponse> searchAllPost(Long boardId, PostSearchCondition searchCondition, Pageable pageable);
    Optional<PostResponse> searchPostById(Long postId);
}
