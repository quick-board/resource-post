package com.quickboard.resourcepost.post.dto;

public record PostUpdate(
    String title,
    String content,
    String guestPassword
) { }
