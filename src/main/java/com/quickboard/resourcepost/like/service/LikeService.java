package com.quickboard.resourcepost.like.service;

import com.quickboard.resourcepost.common.security.dto.Passport;

public interface LikeService {
    void postLikeProcess(Long postId, Passport passport);
    void deleteLikeProcess(Long postId, Passport passport);
}
