package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateBoardRequest {
    private String title;
    private String content;
    private String mem_id;
    private String board_category;

}
