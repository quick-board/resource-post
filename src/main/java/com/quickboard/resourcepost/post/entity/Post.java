package com.quickboard.resourcepost.post.entity;

import com.quickboard.resourcepost.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "posts")
@DynamicInsert
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String title;

    @Lob
    @Setter
    private String content;

    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @Column(name = "profile_id")
    private Long profileId;

    private String guestPassword;

    @Builder
    public Post(String title, String content, Long boardId, Long profileId, String guestPassword) {
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.profileId = profileId;
        this.guestPassword = guestPassword;
    }
}
