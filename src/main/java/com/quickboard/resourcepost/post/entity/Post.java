package com.quickboard.resourcepost.post.entity;

import com.quickboard.resourcepost.board.entity.Board;
import com.quickboard.resourcepost.common.entity.BaseEntity;
import com.quickboard.resourcepost.profile.entity.Profile;
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

    @Column(nullable = false)
    @Setter
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Profile profile;

    @Setter
    private String guestPassword;

    @Builder
    public Post(String title, String content, Board board) {
        this.title = title;
        this.content = content;
        this.board = board;
    }

    //todo 스키마에 디폴트값 추가
}
