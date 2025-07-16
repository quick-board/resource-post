package com.quickboard.resourcepost.post.dto;

import java.time.Instant;

public record PostResponse(
        Long id,
        String title,
        String content,
        Long boardId,
        Long profileId,
        Long likes,
        Instant createdAt,
        Instant updatedAt
) { }
