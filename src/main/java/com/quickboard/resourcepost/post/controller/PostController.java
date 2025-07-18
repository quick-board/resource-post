package com.quickboard.resourcepost.post.controller;

import com.quickboard.resourcepost.common.dto.AuthorCredential;
import com.quickboard.resourcepost.post.dto.PostCreatePayload;
import com.quickboard.resourcepost.post.dto.PostResponse;
import com.quickboard.resourcepost.post.dto.PostSearchCondition;
import com.quickboard.resourcepost.post.dto.PostUpdatePayload;
import com.quickboard.resourcepost.post.service.PostService;
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

    private final PostService postService;

    @GetMapping("/boards/{id}/posts")
    public Page<PostResponse> getAllPosts(@PathVariable("id") Long boardId,
                                          @ModelAttribute @ParameterObject PostSearchCondition searchCondition,
                                          @ParameterObject @PageableDefault(size = 20, sort = "created-at", direction = Sort.Direction.DESC)
                                              Pageable pageable){
        return postService.searchAllPost(boardId, searchCondition, pageable);
    }

    @GetMapping("/posts/{id}")
    public PostResponse getPostById(@PathVariable("id") Long postId){
        return postService.searchPostById(postId);
    }

    @PostMapping("/boards/{id}/posts")
    public void postPost(@PathVariable("id") Long boardId,
                         @RequestBody PostCreatePayload payload){
        postService.postPost(boardId, payload);
    }

    @PatchMapping("/posts/{id}")
    public void patchPost(@PathVariable("id") Long postId,
                          @RequestBody PostUpdatePayload payload){
        postService.patchPost(postId, payload);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") Long postId,
                           @RequestBody AuthorCredential authorCredential){
        postService.deletePost(postId, authorCredential);
    }
}
