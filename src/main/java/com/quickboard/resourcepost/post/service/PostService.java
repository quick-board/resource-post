package com.quickboard.resourcepost.post.service;

import com.quickboard.resourcepost.common.dto.AuthorCredential;
import com.quickboard.resourcepost.post.dto.PostCreatePayload;
import com.quickboard.resourcepost.post.dto.PostResponse;
import com.quickboard.resourcepost.post.dto.PostSearchCondition;
import com.quickboard.resourcepost.post.dto.PostUpdatePayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<PostResponse> searchAllPost(Long boardId, PostSearchCondition searchCondition, Pageable pageable);
    PostResponse searchPostById(Long postId);
    void postPost(Long boardId, PostCreatePayload payload);
    void patchPost(Long postId, PostUpdatePayload payload);
    void deletePost(Long postId, AuthorCredential authorCredential);
}
