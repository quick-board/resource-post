package com.quickboard.resourcepost.like.repository;

import com.quickboard.resourcepost.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LikeRepository extends JpaRepository<Like, Long> {
    @Modifying
    @Query("delete from Like l where l.post.id = :postId and l.profileId = :profileId")
    int deleteByPostIdAndProfileId(@Param("postId") Long postId, @Param("profileId") Long profileId);

    @Modifying
    @Query("delete from Like l where l.post.id = :postId and l.guestUuid = :guestUuid")
    int deleteByPostAndGuestUuid(@Param("postId") Long postId, @Param("guestUuid") String guestUuid);
}
