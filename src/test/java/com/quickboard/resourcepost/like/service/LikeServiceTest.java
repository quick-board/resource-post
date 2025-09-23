package com.quickboard.resourcepost.like.service;

import com.quickboard.resourcepost.like.repository.LikeRepository;
import com.quickboard.resourcepost.like.service.impl.LikeServiceImpl;
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
}