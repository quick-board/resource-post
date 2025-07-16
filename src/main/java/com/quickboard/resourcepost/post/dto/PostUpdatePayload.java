package com.quickboard.resourcepost.post.dto;

import com.quickboard.resourcepost.common.dto.AuthorCredential;

public record PostUpdatePayload(
    AuthorCredential authorCredential,
    PostUpdate postUpdate
) { }
