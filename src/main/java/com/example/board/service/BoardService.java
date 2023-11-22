package com.example.Board.Service;


import com.example.Board.Dto.*;
import com.example.Board.Entity.Board;
import com.example.Board.Repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    public Board save(AddBoardRequest request) {

        return boardRepository.save(request.toEntity());
    }

    public List<Board> findAll() {

        return boardRepository.findAll();
    }

    public Board findById(long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {

        boardRepository.deleteById(id);
    }

    @Transactional
    public Board update(long id, UpdateBoardRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        board.update(request.getTitle(), request.getContent(), request.getMem_id(), request.getBoard_category());

        return board;
    }
}