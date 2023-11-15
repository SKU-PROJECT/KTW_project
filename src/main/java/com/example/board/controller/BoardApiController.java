package com.example.board.controller;

import com.example.board.entity.Board;
import com.example.board.dto.AddBoardRequest;
import com.example.board.dto.BoardViewResponse;
import com.example.board.dto.UpdateBoardRequest;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity<Board> addBoard(@RequestBody AddBoardRequest request) {
        Board savedBoard = boardService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBoard);
    }
    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardViewResponse>> findAllBoards() {
        List<BoardViewResponse> boards = boardService.findAll()
                .stream()
                .map(BoardViewResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<BoardViewResponse> findBoard(@PathVariable long id) {
        Board board = boardService.findById(id);

        return ResponseEntity.ok()
                .body(new BoardViewResponse(board));
    }

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
        boardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable long id,
                                               @RequestBody UpdateBoardRequest request) {
        Board updatedBoard = boardService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedBoard);
    }

}