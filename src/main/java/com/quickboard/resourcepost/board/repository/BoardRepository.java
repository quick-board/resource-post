package com.quickboard.resourcepost.board.repository;

import com.quickboard.resourcepost.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByName(String name);
}
