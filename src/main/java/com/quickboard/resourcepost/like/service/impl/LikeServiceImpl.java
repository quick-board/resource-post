package com.quickboard.resourcepost.like.service.impl;

import com.quickboard.resourcepost.common.dto.AuthorIdentity;
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
    public void postLikeProcess(Long postId, AuthorIdentity authorIdentity) {
        if(!isValidFormat(authorIdentity)){
            throw new LikeAuthorFormatException();
        }

        Post refPost = entityManager.getReference(Post.class, postId);
        Like newObject = Like.builder()
                .post(refPost)
                .profileId(authorIdentity.profileId())
                .guestUuid(authorIdentity.guestUuid())
                .build();

        likeRepository.save(newObject);
    }

    @Transactional
    @Override
    public void deleteLikeProcess(Long postId, AuthorIdentity authorIdentity) {
        if(!isValidFormat(authorIdentity)){
            throw new LikeAuthorFormatException();
        }
        int result;

        if(Objects.nonNull(authorIdentity.profileId())){
            result = likeRepository.deleteByPostIdAndProfileId(postId, authorIdentity.profileId());
        }
        else {
            result = likeRepository.deleteByPostAndGuestUuid(postId, authorIdentity.guestUuid());
        }

        if(result >= 2){
            throw new IllegalArgumentException();
        }
        else if(result <= 0){
            throw new LikeNotFoundException();
        }

    }

    private static boolean isValidFormat(AuthorIdentity authorIdentity){
        boolean isMember = Objects.nonNull(authorIdentity.profileId());
        boolean isGuest = Objects.nonNull(authorIdentity.guestUuid());

        return isMember != isGuest;
    }

    private static boolean isOwner(Like like, AuthorIdentity authorIdentity){
        boolean member = Objects.isNull(like.getGuestUuid()) && Objects.equals(like.getProfileId(), authorIdentity.profileId());
        boolean guest = Objects.isNull(like.getProfileId()) && Objects.equals(like.getGuestUuid(), authorIdentity.guestUuid());

        return member || guest;
    }
}
