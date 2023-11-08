package com.example.board.dto;

import com.example.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class BoardViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;

    public BoardViewResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = LocalDate.from(board.getCreatedAt());
    }
}
