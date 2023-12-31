package com.example.Board.Repository;

import com.example.Board.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 페이징
    Page<Board> findAll(Pageable pageable);
}