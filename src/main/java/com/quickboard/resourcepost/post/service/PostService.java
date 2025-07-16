package com.quickboard.resourcepost.post.service;

import com.quickboard.resourcepost.post.dto.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<PostResponse> searchAllPost(String title, String content, Long profileId, Pageable pageable);
}
