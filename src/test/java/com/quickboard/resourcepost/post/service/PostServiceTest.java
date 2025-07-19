package com.quickboard.resourcepost.post.service;

import com.quickboard.resourcepost.common.dto.AuthorCredential;
import com.quickboard.resourcepost.post.dto.*;
import com.quickboard.resourcepost.post.entity.Post;
import com.quickboard.resourcepost.post.exception.impl.PostAuthorFormException;
import com.quickboard.resourcepost.post.exception.impl.PostAuthorNotOwnerException;
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

    @Test
    void postPost() {
        Long boardId = 1L;
        AuthorCredential credential = new AuthorCredential(1L, null);
        PostCreate postCreate = new PostCreate("title", "content");
        PostCreatePayload postCreatePayload = new PostCreatePayload(credential, postCreate);
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(new Post());

        postService.postPost(boardId, postCreatePayload);

        Mockito.verify(postRepository, Mockito.times(1))
                .save(Mockito.any(Post.class));
    }

    @Test
    void patchPost() {
        Long postId = 1L;
        AuthorCredential credential = new AuthorCredential(100L, null);
        PostUpdate postUpdate = new PostUpdate("after", "after");
        PostUpdatePayload postUpdatePayload = new PostUpdatePayload(credential, postUpdate);
        Post post = Post.builder()
                .title("before")
                .content("before")
                .profileId(credential.profileId())
                .build();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        postService.patchPost(postId, postUpdatePayload);

        Assertions.assertAll(
                () -> Assertions.assertEquals(postUpdate.title(), post.getTitle()),
                () -> Assertions.assertEquals(postUpdate.content(), post.getContent())
        );
    }

    @Test
    void patchPostInvalidForm() {
        Long postId = 1L;
        AuthorCredential exists = new AuthorCredential(1L, "apple");
        AuthorCredential nulls = new AuthorCredential(null, null);

        Assertions.assertAll(
                () -> Assertions.assertThrows(PostAuthorFormException.class,
                        () -> postService.patchPost(postId, new PostUpdatePayload(exists, null))),
                () -> Assertions.assertThrows(PostAuthorFormException.class,
                        () -> postService.patchPost(postId, new PostUpdatePayload(nulls, null)))
        );
    }

    @Test
    void patchPostNotAuthor() {
        Long postId = 1L;
        AuthorCredential credential = new AuthorCredential(1L, null);
        PostUpdatePayload postUpdatePayload = new PostUpdatePayload(credential, new PostUpdate(null, null));

        Post post = Post.builder().profileId(2L).build();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        Assertions.assertThrows(PostAuthorNotOwnerException.class, () -> postService.patchPost(postId, postUpdatePayload));
    }

    @Test
    void deletePost() {
        Long postId = 1L;
        AuthorCredential credential = new AuthorCredential(100L, null);
        Post post = Post.builder().profileId(100L).build();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        postService.deletePost(postId, credential);

        Mockito.verify(postRepository, Mockito.times(1)).delete(post);
    }
}