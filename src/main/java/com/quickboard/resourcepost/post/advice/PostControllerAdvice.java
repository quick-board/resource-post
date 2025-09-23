package com.quickboard.resourcepost.post.advice;

import com.quickboard.resourcepost.post.exception.impl.PostAuthorFormException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostControllerAdvice {

    @ExceptionHandler(PostAuthorFormException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo authorExceptionHandling(HttpServletRequest request, Exception e){
        return ErrorInfo.builder()
                .url(request.getRequestURI())
                .ex(e)
                .build();
    }
}
