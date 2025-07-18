package com.quickboard.resourcepost.post.exception;

import com.quickboard.resourcepost.common.exception.AuthorException;

public abstract class PostAuthorException extends AuthorException {
    public PostAuthorException(Throwable cause) {
        super(cause);
    }

    public PostAuthorException() {
    }
}
