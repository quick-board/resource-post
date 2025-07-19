package com.quickboard.resourcepost.like.service;

import com.quickboard.resourcepost.common.dto.AuthorIdentity;
import com.quickboard.resourcepost.like.entity.Like;
import com.quickboard.resourcepost.like.repository.LikeRepository;
import com.quickboard.resourcepost.like.service.impl.LikeServiceImpl;
import com.quickboard.resourcepost.post.entity.Post;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LikeServiceTest {

    private LikeService likeService;
    private LikeRepository likeRepository;
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        likeRepository = Mockito.mock(LikeRepository.class);
        entityManager = Mockito.mock(EntityManager.class);
        likeService = new LikeServiceImpl(likeRepository, entityManager);
    }

    @Test
    void postLikeProcess() {
        AuthorIdentity authorIdentity = new AuthorIdentity(100L, null);
        Long postId = 1L;
        Mockito.when(entityManager.getReference(Post.class, postId)).thenReturn(new Post());
        Mockito.when(likeRepository.save(Mockito.any(Like.class))).thenReturn(new Like());

        likeService.postLikeProcess(postId, authorIdentity);

        Mockito.verify(likeRepository, Mockito.times(1)).save(Mockito.any(Like.class));
    }

    @Test
    void deleteLikeProcess() {
        AuthorIdentity authorIdentity = new AuthorIdentity(100L, null);
        Long postId = 1L;
        Mockito.when(likeRepository.deleteByPostIdAndProfileId(postId, authorIdentity.profileId())).thenReturn(1);

        likeService.deleteLikeProcess(postId, authorIdentity);

        Mockito.verify(likeRepository, Mockito.times(1))
                .deleteByPostIdAndProfileId(postId, authorIdentity.profileId());
    }
}