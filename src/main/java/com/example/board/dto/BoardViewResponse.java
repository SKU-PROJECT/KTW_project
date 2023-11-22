package com.example.Board.Dto;

import com.example.Board.Entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
public class BoardViewResponse {
    private Long id;
    private String title;
    private String content;

    private String mem_id;
    private String board_category;
    private Date createdAt;

    public BoardViewResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.mem_id = board.getMem_id();
        this.board_category = board.getBoard_category();
        this.createdAt = board.getCreatedAt();
    }
}