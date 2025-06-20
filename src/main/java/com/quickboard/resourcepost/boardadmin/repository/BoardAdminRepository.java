package com.quickboard.resourcepost.boardadmin.repository;

import com.quickboard.resourcepost.boardadmin.entity.BoardAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardAdminRepository extends JpaRepository<BoardAdmin, Long> {
}
