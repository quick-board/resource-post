package com.quickboard.resourcepost.like.entity;

import com.quickboard.resourcepost.common.entity.BaseEntity;
import com.quickboard.resourcepost.post.entity.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "likes")
@DynamicInsert
@Getter
@NoArgsConstructor
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "guest_uuid")
    private String guestUuid;

    @Builder
    public Like(Post post, Long profileId, String guestUuid) {
        this.post = post;
        this.profileId = profileId;
        this.guestUuid = guestUuid;
    }
}
