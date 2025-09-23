package com.quickboard.resourcepost.post.service;

import com.quickboard.resourcepost.post.dto.*;
import com.quickboard.resourcepost.post.exception.impl.PostNotFoundException;
import com.quickboard.resourcepost.post.repository.PostRepository;
import com.quickboard.resourcepost.post.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    private PostService postService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = Mockito.mock(PostRepository.class);
        postService = new PostServiceImpl(postRepository);
    }

    @Test
    void searchAllPost() {
        Long boardId = 1L;
        PostSearchCondition searchCondition = new PostSearchCondition();
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(postRepository.searchAllPost(boardId, searchCondition, pageable))
                .thenReturn(new PageImpl<>(List.of()));

        postService.searchAllPost(boardId, searchCondition, pageable);

        Mockito.verify(postRepository, Mockito.times(1))
                .searchAllPost(boardId, searchCondition, pageable);
    }

    @Test
    void searchPostByIdFail() {
        Long postId = 1L;
        Mockito.when(postRepository.searchPostById(postId)).thenThrow(PostNotFoundException.class);

        Assertions.assertThrows(PostNotFoundException.class, () -> postService.searchPostById(postId));
    }

    @Test
    void searchPostByIdSuccess() {
        Long postId = 1L;
        PostResponse postResponse = new PostResponse(null, null, null, null, null, null, null, null);
        Mockito.when(postRepository.searchPostById(postId)).thenReturn(Optional.of(postResponse));

        postService.searchPostById(postId);

        Mockito.verify(postRepository, Mockito.times(1))
                .searchPostById(postId);
    }

}