package com.quickboard.resourcepost.post.controller;

import com.quickboard.resourcepost.common.dto.AuthorCredential;
import com.quickboard.resourcepost.post.dto.PostCreatePayload;
import com.quickboard.resourcepost.post.dto.PostResponse;
import com.quickboard.resourcepost.post.dto.PostUpdatePayload;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rsc/v1")
public class PostController {

    @GetMapping("/boards/{id}/posts")
    public Page<PostResponse> getAllPosts(@PathVariable("id") Long boardId,
                                          @RequestParam(value = "title", required = false) String title,
                                          @RequestParam(value = "content", required = false) String content,
                                          @RequestParam(value = "author", required = false) Long profileId,
                                          @ParameterObject @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
                                              Pageable pageable){
        return null;
    }

    @GetMapping("/posts/{id}")
    public PostResponse getPostById(@PathVariable("id") Long postId){
        return null;
    }

    @PostMapping("/boards/{id}/posts")
    public void postPost(@PathVariable("id") Long boardId,
                         @RequestBody PostCreatePayload payload){

    }

    @PatchMapping("/posts/{id}")
    public void patchPost(@PathVariable("id") Long postId,
                          @RequestBody PostUpdatePayload payload){

    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") Long postId,
                           @RequestBody AuthorCredential authorCredential){}
}
