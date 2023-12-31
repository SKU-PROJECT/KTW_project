package com.example.Board.Dto;

import com.example.Board.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBoardRequest {
    private String title;
    private String content;
    private String mem_id;
    private String board_category;

    private String createdAt;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .mem_id(mem_id)
                .board_category(board_category)
                .build();
    }
}