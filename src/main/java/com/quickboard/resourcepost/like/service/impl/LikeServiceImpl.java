package com.quickboard.resourcepost.like.service.impl;

import com.quickboard.resourcepost.common.security.dto.Passport;
import com.quickboard.resourcepost.common.security.enums.CallerType;
import com.quickboard.resourcepost.like.entity.Like;
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
    public Long postLikeProcess(Long postId, Passport passport) {
        Post refPost = entityManager.getReference(Post.class, postId);
        Long profileId = passport.callerType() == CallerType.END_USER ? passport.endUserDetails().profileId() : null;
        String guestUuid = passport.callerType() == CallerType.ANONYMOUS ? passport.anonymousDetails().guestId() : null;
        Like newObject = Like.builder()
                .post(refPost)
                .profileId(profileId)
                .guestUuid(guestUuid)
                .build();

        Like saved = likeRepository.save(newObject);
        return saved.getId();
    }

    @Transactional
    @Override
    public void deleteLikeProcess(Long postId, Passport passport) {
        int result = 0;

        if(passport.callerType() == CallerType.END_USER){
            result = likeRepository.deleteByPostIdAndProfileId(postId, passport.endUserDetails().profileId());
        }
        else if(passport.callerType() == CallerType.ANONYMOUS){
            result = likeRepository.deleteByPostAndGuestUuid(postId, passport.anonymousDetails().guestId());
        }

        if(result >= 2){
            throw new IllegalArgumentException();
        }
        else if(result <= 0){
            throw new LikeNotFoundException();
        }

    }

    private static boolean isOwner(Like like, Passport passport){
        if(passport.callerType() == CallerType.ANONYMOUS){
            return like.getGuestUuid().equals(passport.anonymousDetails().guestId());
        }
        else if (passport.callerType() == CallerType.END_USER) {
            return like.getProfileId().equals(passport.endUserDetails().profileId());
        }
        return false;
    }
}
