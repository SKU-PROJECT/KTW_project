package com.example.Board.Dto;

import com.example.Board.Entity.Board;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@NoArgsConstructor
@Getter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String file_name;
    private String file_url;
    private String file_extension;
    private String file_category;
    private Long file_key;


    public File(MultipartFile multipartFile, Board board) {
        this.file_name = multipartFile.getOriginalFilename();
        this.file_url = "http://localhost:8080/board/files/" + board.getId() + "_" + this.file_name;
        this.file_extension = this.file_name.substring(this.file_name.lastIndexOf(".") + 1);
        this.file_category = "board";
        this.file_key = board.getId();
    }

}