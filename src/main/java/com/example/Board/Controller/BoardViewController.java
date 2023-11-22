package com.example.Board.Controller;

import com.example.Board.Dto.BoardViewResponse;
import com.example.Board.Entity.Board;
import com.example.Board.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BoardViewController {
    private final BoardService boardService;

//    @GetMapping("/boards")
//    public String getBoards(Model model) {
//        List<BoardViewResponse> boards = boardService.findAll()
//                .stream()
//                .map(BoardViewResponse::new)
//                .toList();
//
//        model.addAttribute("boards", boards);
//
//        return "board/boardList";
//    }

    // 전체 게시판 리스트 페이징
    @GetMapping("/boards")
    public String getBoards(Model model, @RequestParam(value="page", defaultValue = "0") int page){
        Page<Board> boardsPaging = this.boardService.findAll(page);
        model.addAttribute("boardPaging", boardsPaging);
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