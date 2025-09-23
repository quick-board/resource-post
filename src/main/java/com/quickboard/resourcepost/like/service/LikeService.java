package com.quickboard.resourcepost.like.service;

import com.quickboard.resourcepost.common.security.dto.Passport;

public interface LikeService {
    Long postLikeProcess(Long postId, Passport passport);
    void deleteLikeProcess(Long postId, Passport passport);
}
