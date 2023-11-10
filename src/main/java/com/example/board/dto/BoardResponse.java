package com.example.board.dto;

import com.example.board.domain.Board;
import lombok.Getter;

@Getter
public class BoardResponse {
    private final String title;
    private final String content;

    private final String mem_id;

    private final String board_category;

    public BoardResponse(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.mem_id = board.getMem_id();
        this.board_category = board.getBoard_category();
    }
}