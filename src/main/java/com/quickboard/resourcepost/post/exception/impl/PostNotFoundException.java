package com.quickboard.resourcepost.post.exception.impl;

import com.quickboard.resourcepost.common.exception.ResourceNotFoundException;

public class PostNotFoundException extends ResourceNotFoundException {
    public PostNotFoundException() {
    }

    public PostNotFoundException(Throwable cause) {
        super(cause);
    }
}
