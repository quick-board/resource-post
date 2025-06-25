package com.quickboard.resourcepost.comment.entity;

import com.quickboard.resourcepost.common.entity.BaseEntity;
import com.quickboard.resourcepost.post.entity.Post;
import com.quickboard.resourcepost.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "comments")
@DynamicInsert
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private Profile profile;

    @Column(name = "guest_password")
    private String guestPassword;
}
