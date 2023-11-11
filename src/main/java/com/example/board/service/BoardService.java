package com.example.Board.service;

import com.example.Board.entity.Board;
import com.example.Board.dto.AddBoardRequest;
import com.example.Board.dto.UpdateBoardRequest;
import com.example.Board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
