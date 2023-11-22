package com.example.Board.Repository;

import com.example.Board.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
