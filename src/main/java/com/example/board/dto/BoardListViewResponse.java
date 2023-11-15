package com.example.board.dto;

import com.example.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardListViewResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String mem_id;
    private final String board_category;


    public BoardListViewResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.mem_id = board.getMem_id();
        this.board_category = board.getBoard_category();
    }
}
