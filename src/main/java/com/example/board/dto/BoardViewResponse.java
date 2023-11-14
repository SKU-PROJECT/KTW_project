package com.example.Board.dto;

import com.example.Board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    //private List<FileFormat> fileList;

    public BoardViewResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.mem_id = board.getMem_id();
        this.board_category = board.getBoard_category();
        this.createdAt = board.getCreatedAt();
    }

//    public BoardViewResponse(Board board, List<FileFormat> fileList) {
//        this.id = board.getId();
//        this.title = board.getTitle();
//        this.content = board.getContent();
//        this.mem_id = board.getMem_id();
//        this.board_category = board.getBoard_category();
//        this.createdAt = board.getCreatedAt();
//        this.fileList = fileList;
//    }
}
