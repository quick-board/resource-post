package com.quickboard.resourcepost.post.service;

import com.quickboard.resourcepost.common.dto.AuthorCredential;
import com.quickboard.resourcepost.common.security.dto.Passport;
import com.quickboard.resourcepost.post.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<PostResponse> searchAllPost(Long boardId, PostSearchCondition searchCondition, Pageable pageable);
    PostResponse searchPostById(Long postId);
    Long postPost(Long boardId, PostCreate postCreate, Passport passport);
    Long patchPost(Long postId, PostUpdate postUpdate, Passport passport);
    void deletePost(Long postId, AuthorCredential authorCredential, Passport passport);
}
