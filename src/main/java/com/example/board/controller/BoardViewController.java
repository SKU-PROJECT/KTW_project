package com.example.Board.controller;

import com.example.Board.dto.FileFormat;
import com.example.Board.entity.Board;
import com.example.Board.dto.BoardViewResponse;
import com.example.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardViewController {
    private final BoardService boardService;

    @GetMapping("/boards")
    public String getBoards(Model model) {
        List<BoardViewResponse> boards = boardService.findAll()
                .stream()
                .map(BoardViewResponse::new)
                .toList();

        model.addAttribute("boards", boards);

        return "board/boardList";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id);

       // List<FileFormat> fileFormatList = boardService.findFileByBoard(id);

       /* if (fileFormatList != null) {
            model.addAttribute("board", new BoardViewResponse(board, fileFormatList));
        } else {
            model.addAttribute("board", new BoardViewResponse(board));
        }*/

        model.addAttribute("board", new BoardViewResponse(board));
//        model.addAttribute("board", new BoardViewResponse(board, fileFormatList));
//        model.addAttribute("fileList", fileFormatList);

        return "board/board";
    }


    @GetMapping("/new-board")
    public String newBoard(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("board", new BoardViewResponse());
        } else {
            Board board = boardService.findById(id);
            model.addAttribute("board", new BoardViewResponse(board));
        }

        return "board/newBoard";
    }



}