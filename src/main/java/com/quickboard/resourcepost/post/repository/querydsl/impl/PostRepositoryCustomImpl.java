package com.quickboard.resourcepost.post.repository.querydsl.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.quickboard.resourcepost.post.dto.PostResponse;
import com.quickboard.resourcepost.post.dto.PostSearchCondition;
import com.quickboard.resourcepost.post.repository.querydsl.PostRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.quickboard.resourcepost.post.entity.QPost.post;
import static com.quickboard.resourcepost.like.entity.QLike.like;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostResponse> searchAllPost(Long boardId, PostSearchCondition searchCondition, Pageable pageable) {
        String title = searchCondition.getTitle();
        String content = searchCondition.getContent();
        Long profileId = searchCondition.getProfileId();

        List<PostResponse> results = queryFactory
                .select(Projections
                        .constructor(PostResponse.class,
                                post.id,
                                post.title,
                                post.content,
                                post.boardId,
                                post.profileId,
                                like.count().as("likes"),
                                post.createdAt,
                                post.updatedAt))
                .from(post)
                .leftJoin(like)
                .on(post.eq(like.post))
                .groupBy(post.id, post.title, post.content, post.boardId, post.profileId, post.createdAt, post.updatedAt)
                .where( post.boardId.eq(boardId),
                        title != null ? post.title.contains(title) : null,
                        content != null ? post.content.contains(content) : null,
                        profileId != null ? post.profileId.eq(profileId) : null)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifiers(pageable.getSort()))
                .fetch();

        Long total = queryFactory
                .select(post.count())
                .from(post)
                .where(post.boardId.eq(boardId),
                        title != null ? post.title.contains(title) : null,
                        content != null ? post.content.contains(content) : null,
                        profileId != null ? post.profileId.eq(profileId) : null)
                .fetchOne();


        return new PageImpl<>(results, pageable, Objects.nonNull(total) ? total : 0);
    }

    @Override
    public Optional<PostResponse> searchPostById(Long postId) {

        return Optional.ofNullable(queryFactory
                .select(Projections
                        .constructor(PostResponse.class,
                            post.id,
                            post.title,
                            post.content,
                            post.boardId,
                            post.profileId,
                            like.count().as("likes"),
                            post.createdAt,
                            post.updatedAt))
                .from(post)
                .leftJoin(like)
                .on(post.eq(like.post))
                .groupBy(post.id, post.title, post.content, post.boardId, post.profileId, post.createdAt, post.updatedAt)
                .where(post.id.eq(postId))
                .fetchOne());
    }


    private static OrderSpecifier<?>[] getOrderSpecifiers(Sort sort){
        List<OrderSpecifier<?>> orderSpecifierList = new ArrayList<>();

        sort.get().forEach(order -> {
            OrderSpecifier<?> orderSpecifier = getOrderSpecifier(order);
            if(Objects.nonNull(orderSpecifier)){
                orderSpecifierList.add(orderSpecifier);
            }
        });

        if(orderSpecifierList.isEmpty()){
            orderSpecifierList.add(new OrderSpecifier<>(Order.DESC, post.createdAt));
        }

        return orderSpecifierList.toArray(new OrderSpecifier[0]);
    }


    private static OrderSpecifier<?> getOrderSpecifier(Sort.Order order) {
        String propertyName = order.getProperty();
        Order direction = order.getDirection().isDescending() ? Order.DESC : Order.ASC;

        return switch (propertyName) {
            case "title" -> new OrderSpecifier<>(direction, post.title);
            case "created-at" -> new OrderSpecifier<>(direction, post.createdAt);
            case "likes" -> new OrderSpecifier<>(direction, like.count());
            default -> null;
        };
    }
}
