package com.example.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name="mem_id", nullable = false)
    private String mem_id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name="board_category", nullable = false)
    private String board_category;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;


    @Builder
    public Board(String title, String content, String mem_id, String board_category) {
        this.title = title;
        this.content = content;
        this.mem_id = mem_id;
        this.board_category = board_category;
    }

    public void update(String title, String content, String mem_id, String board_category) {
        this.title = title;
        this.content = content;
        this.mem_id = mem_id;
        this.board_category = board_category;
    }
}
