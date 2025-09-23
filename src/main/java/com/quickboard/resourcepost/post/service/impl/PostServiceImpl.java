package com.quickboard.resourcepost.post.service.impl;

import com.quickboard.resourcepost.common.dto.AuthorCredential;
import com.quickboard.resourcepost.common.security.dto.Passport;
import com.quickboard.resourcepost.common.security.enums.CallerType;
import com.quickboard.resourcepost.post.dto.*;
import com.quickboard.resourcepost.post.entity.Post;
import com.quickboard.resourcepost.post.exception.impl.PostAuthorFormException;
import com.quickboard.resourcepost.post.exception.impl.PostAuthorNotOwnerException;
import com.quickboard.resourcepost.post.exception.impl.PostNotFoundException;
import com.quickboard.resourcepost.post.repository.PostRepository;
import com.quickboard.resourcepost.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<PostResponse> searchAllPost(Long boardId, PostSearchCondition searchCondition, Pageable pageable) {
        return postRepository.searchAllPost(boardId, searchCondition, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public PostResponse searchPostById(Long postId) {
        return postRepository.searchPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Transactional
    @Override
    public Long postPost(Long boardId, PostCreate postCreate, Passport passport) {

        Long profileId = passport.callerType() == CallerType.END_USER ? passport.endUserDetails().profileId() : null;

        if(!validateForm(profileId, postCreate.guestPassword())){
            throw new PostAuthorFormException();
        }

        Post newObject = Post.builder()
                .title(postCreate.title())
                .content(postCreate.content())
                .boardId(boardId)
                .profileId(profileId)
                .guestPassword(postCreate.guestPassword())
                .build();

        Post post = postRepository.save(newObject);

        return post.getId();
    }

    @Transactional
    @Override
    public Long patchPost(Long postId, PostUpdate postUpdate, Passport passport) {

        Long profileId = passport.callerType() == CallerType.END_USER ? passport.endUserDetails().profileId() : null;

        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        if(!isAuthor(post, profileId, postUpdate.guestPassword())){
            throw new PostAuthorNotOwnerException();
        }

        post.setTitle(postUpdate.title());
        post.setContent(postUpdate.content());

        return post.getId();
    }

    @Transactional
    @Override
    public void deletePost(Long postId, AuthorCredential authorCredential, Passport passport) {

        Long profileId = passport.callerType() == CallerType.END_USER ? passport.endUserDetails().profileId() : null;

        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        if(!isAuthor(post, profileId, authorCredential.guestPassword())){
            throw new PostAuthorNotOwnerException();
        }

        postRepository.delete(post);
    }

    private static boolean validateForm(Long profileId, String guestPassword){
        boolean member = Objects.nonNull(profileId);
        boolean guest = Objects.nonNull(guestPassword);

        return member != guest;
    }

    private static boolean isAuthor(Post post, Long profileId, String guestPassword){
        boolean member = Objects.nonNull(post.getProfileId()) &&
                Objects.equals(post.getProfileId(), profileId);

        boolean guest = Objects.nonNull(post.getGuestPassword()) &&
                Objects.equals(post.getGuestPassword(), guestPassword);

        return  member || guest;

    }
}
