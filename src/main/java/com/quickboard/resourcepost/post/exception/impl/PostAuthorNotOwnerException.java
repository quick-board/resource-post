package com.quickboard.resourcepost.post.exception.impl;

import com.quickboard.resourcepost.post.exception.PostAuthorException;

public class PostAuthorNotOwnerException extends PostAuthorException {
    public PostAuthorNotOwnerException(Throwable cause) {
        super(cause);
    }

    public PostAuthorNotOwnerException() {
    }
}
