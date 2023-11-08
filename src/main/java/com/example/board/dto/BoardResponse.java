package com.example.board.dto;

import com.example.board.domain.Board;
import lombok.Getter;

@Getter
public class BoardResponse {
    private final String title;
    private final String content;

    public BoardResponse(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}