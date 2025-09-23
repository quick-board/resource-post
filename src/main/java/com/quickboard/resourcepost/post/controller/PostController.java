package com.quickboard.resourcepost.post.controller;

import com.quickboard.resourcepost.common.dto.AuthorCredential;
import com.quickboard.resourcepost.common.security.dto.Passport;
import com.quickboard.resourcepost.post.dto.*;
import com.quickboard.resourcepost.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rsc/v1")
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/boards/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostResponse> getAllPosts(@PathVariable("id") Long boardId,
                                          @ModelAttribute @ParameterObject PostSearchCondition searchCondition,
                                          @ParameterObject @PageableDefault(size = 20, sort = "created-at", direction = Sort.Direction.DESC)
                                              Pageable pageable){
        return postService.searchAllPost(boardId, searchCondition, pageable);
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPostById(@PathVariable("id") Long postId){
        return postService.searchPostById(postId);
    }

    @PostMapping("/boards/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Long postPost(@PathVariable("id") Long boardId,
                         @RequestBody PostCreate postCreate,
                         Passport passport){
        return postService.postPost(boardId, postCreate, passport);
    }

    @PatchMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Long patchPost(@PathVariable("id") Long postId,
                          @RequestBody PostUpdate postUpdate,
                          Passport passport){
        return postService.patchPost(postId, postUpdate, passport);
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") Long postId,
                           @RequestBody AuthorCredential authorCredential,
                           Passport passport){
        postService.deletePost(postId, authorCredential, passport);
    }
}
