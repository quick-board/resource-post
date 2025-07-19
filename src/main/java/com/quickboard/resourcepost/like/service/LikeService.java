package com.quickboard.resourcepost.like.service;

import com.quickboard.resourcepost.common.dto.AuthorIdentity;

public interface LikeService {
    void postLikeProcess(Long postId, AuthorIdentity authorIdentity);
    void deleteLikeProcess(Long postId, AuthorIdentity authorIdentity);
}
