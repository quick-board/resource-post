package com.quickboard.resourcepost.like.exception.impl;

import com.quickboard.resourcepost.like.exception.LikeAuthorException;

public class LikeAuthorNotOwnerException extends LikeAuthorException {
    public LikeAuthorNotOwnerException(Throwable cause) {
        super(cause);
    }

    public LikeAuthorNotOwnerException() {
    }
}
