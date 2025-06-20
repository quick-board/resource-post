package com.quickboard.resourcepost.post.entity;

import com.quickboard.resourcepost.board.entity.Board;
import com.quickboard.resourcepost.common.entity.BaseEntity;
import jakarta.persistence.*;
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

    @Column(name = "writer_id")
    private Long writerId;

    @Setter
    private String guestPassword;

    //todo createdAt, updatedAt 추가
    //todo @DynamicInsert와 디폴트 추가
}
