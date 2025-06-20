package com.quickboard.resourcepost.board.repository;

import com.quickboard.resourcepost.board.entity.Board;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Board board = Board.builder()
                .name("운동")
                .description("운동을 합시다")
                .isWritable(true).build();
        boardRepository.save(board);
    }

    @Test
    void selectTest() {
        Board found = boardRepository.findByName("운동").orElseThrow();

        assertAll(() -> {
            assertEquals("운동", found.getName());
            assertEquals("운동을 합시다", found.getDescription());
            assertEquals(true, found.getIsWritable());
        });
    }

    @Test
    void updateTest() {
        String newDescription = "운동이 싫어요";
        Board board = boardRepository.findByName("운동").orElseThrow();
        board.setDescription(newDescription);

        entityManager.flush();
        entityManager.clear();

        Board found = boardRepository.findByName("운동").orElseThrow();
        assertEquals(newDescription, found.getDescription());
    }

    @Test
    void deleteTest() {
        Board board = boardRepository.findByName("운동").orElseThrow();
        Long boardId = board.getId();

        boardRepository.delete(board);

        assertFalse(boardRepository.existsById(boardId));
    }
}