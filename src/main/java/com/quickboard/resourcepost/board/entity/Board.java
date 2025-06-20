package com.quickboard.resourcepost.board.entity;

import com.quickboard.resourcepost.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "boards")
@DynamicInsert
@Getter
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    private String description;

    @Column(name = "is_writable", nullable = false)
    @Setter
    private Boolean isWritable;
}
