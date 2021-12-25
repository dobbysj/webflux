package com.webflux.www.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table("board")
public class Board extends Auditor{

    @Id
    @Column("board_id")
    private Long boardId;

    @Column("title")
    private String title;

    @Column("content")
    private String content;

    @Column("writer")
    private String writer;

    @Column("delete_yn")
    private String deleteYn;

    @Builder
    public Board(Long boardId, String title, String content, String writer, String deleteYn, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.deleteYn = deleteYn;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
