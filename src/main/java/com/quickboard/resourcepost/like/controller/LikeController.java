package com.quickboard.resourcepost.like.controller;

import com.quickboard.resourcepost.common.dto.AuthorIdentity;
import com.quickboard.resourcepost.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rsc/v1")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/posts/{id}/likes")
    @ResponseStatus(HttpStatus.CREATED)
    public void postLike(@PathVariable("id") Long postId,
                         @RequestBody AuthorIdentity authorIdentity){
        likeService.postLikeProcess(postId, authorIdentity);
    }

    @DeleteMapping("/posts/{id}/likes/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLike(@PathVariable("id") Long postId,
                           @RequestBody AuthorIdentity authorIdentity){
        likeService.deleteLikeProcess(postId, authorIdentity);
    }
}
