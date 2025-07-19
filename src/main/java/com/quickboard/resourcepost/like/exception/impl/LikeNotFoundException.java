package com.quickboard.resourcepost.like.exception.impl;

import com.quickboard.resourcepost.common.exception.ResourceNotFoundException;

public class LikeNotFoundException extends ResourceNotFoundException {
    public LikeNotFoundException() {
    }

    public LikeNotFoundException(Throwable cause) {
        super(cause);
    }
}
