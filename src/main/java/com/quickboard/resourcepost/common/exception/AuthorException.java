package com.quickboard.resourcepost.common.exception;

public abstract class AuthorException extends RuntimeException{
    public AuthorException(Throwable cause) {
        super(cause);
    }

    public AuthorException() {
    }
}
