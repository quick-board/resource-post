package com.quickboard.resourcepost.boardadmin.entity;

import com.quickboard.resourcepost.board.entity.Board;
import com.quickboard.resourcepost.common.entity.BaseEntity;
import com.quickboard.resourcepost.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_admins")
@Getter
@NoArgsConstructor
public class BoardAdmin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Profile profile;
}
