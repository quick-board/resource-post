package com.quickboard.resourcepost.like.service.impl;

import com.quickboard.resourcepost.common.security.dto.Passport;
import com.quickboard.resourcepost.like.entity.Like;
import com.quickboard.resourcepost.like.exception.impl.LikeAuthorFormatException;
import com.quickboard.resourcepost.like.exception.impl.LikeNotFoundException;
import com.quickboard.resourcepost.like.repository.LikeRepository;
import com.quickboard.resourcepost.like.service.LikeService;
import com.quickboard.resourcepost.post.entity.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final EntityManager entityManager;

    @Transactional
    @Override
    public void postLikeProcess(Long postId, Passport passport) {
        if(!isValidFormat(passport)){
            throw new LikeAuthorFormatException();
        }

        Post refPost = entityManager.getReference(Post.class, postId);
        Like newObject = Like.builder()
                .post(refPost)
                .profileId(passport.userId())
                .guestUuid(passport.guestId())
                .build();

        likeRepository.save(newObject);
    }

    @Transactional
    @Override
    public void deleteLikeProcess(Long postId, Passport passport) {
        if(!isValidFormat(passport)){
            throw new LikeAuthorFormatException();
        }
        int result;

        if(Objects.nonNull(passport.userId())){
            result = likeRepository.deleteByPostIdAndProfileId(postId, passport.userId());
        }
        else {
            result = likeRepository.deleteByPostAndGuestUuid(postId, passport.guestId());
        }

        if(result >= 2){
            throw new IllegalArgumentException();
        }
        else if(result <= 0){
            throw new LikeNotFoundException();
        }

    }

    private static boolean isValidFormat(Passport passport){
        boolean isMember = Objects.nonNull(passport.userId());
        boolean isGuest = Objects.nonNull(passport.guestId());

        return isMember != isGuest;
    }

    private static boolean isOwner(Like like, Passport passport){
        boolean member = Objects.isNull(like.getGuestUuid()) && Objects.equals(like.getProfileId(), passport.userId());
        boolean guest = Objects.isNull(like.getProfileId()) && Objects.equals(like.getGuestUuid(), passport.guestId());

        return member || guest;
    }
}
