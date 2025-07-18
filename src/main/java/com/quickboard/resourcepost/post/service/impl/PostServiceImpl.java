package com.quickboard.resourcepost.post.service.impl;

import com.quickboard.resourcepost.common.dto.AuthorCredential;
import com.quickboard.resourcepost.post.dto.*;
import com.quickboard.resourcepost.post.entity.Post;
import com.quickboard.resourcepost.post.exception.impl.PostAuthorFormException;
import com.quickboard.resourcepost.post.exception.impl.PostAuthorNotOwnerException;
import com.quickboard.resourcepost.post.exception.impl.PostNotFoundException;
import com.quickboard.resourcepost.post.repository.PostRepository;
import com.quickboard.resourcepost.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
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
    public void postPost(Long boardId, PostCreatePayload payload) {
        AuthorCredential credential = payload.authorCredential();
        PostCreate postCreate = payload.postCreate();

        Post newObject = Post.builder()
                .title(postCreate.title())
                .content(postCreate.content())
                .boardId(boardId)
                .profileId(credential.profileId())
                .guestPassword(credential.guestPassword())
                .build();

        postRepository.save(newObject);
    }

    @Transactional
    @Override
    public void patchPost(Long postId, PostUpdatePayload payload) {
        AuthorCredential credential = payload.authorCredential();
        PostUpdate postUpdate = payload.postUpdate();

        if(!validateForm(credential)){
            throw new PostAuthorFormException();
        }

        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        if(!isAuthor(post, credential)){
            throw new PostAuthorNotOwnerException();
        }

        post.setTitle(postUpdate.title());
        post.setContent(postUpdate.content());
    }

    @Transactional
    @Override
    public void deletePost(Long postId, AuthorCredential authorCredential) {
        if(!validateForm(authorCredential)){
            throw new PostAuthorFormException();
        }

        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        if(!isAuthor(post, authorCredential)){
            throw new PostAuthorNotOwnerException();
        }

        postRepository.delete(post);
    }

    private static boolean validateForm(AuthorCredential credential){
        boolean member = Objects.nonNull(credential.profileId());
        boolean guest = Objects.nonNull(credential.guestPassword());

        return member != guest;
    }

    private static boolean isAuthor(Post post, AuthorCredential credential){
        boolean member = Objects.nonNull(post.getProfileId()) &&
                Objects.equals(post.getProfileId(), credential.profileId());

        boolean guest = Objects.nonNull(post.getGuestPassword()) &&
                Objects.equals(post.getGuestPassword(),credential.guestPassword());

        return  member || guest;

    }
}
