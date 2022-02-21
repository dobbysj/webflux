package com.webflux.www.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table("Board")
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

    @Column("writer_id")
    private Long writerId;

    @Column("delete_yn")
    private String deleteYn;

    @With
    @Transient
    private List<Comment> comments;

    @With
    @Transient
    private User user;

    public Board withComments(List<Comment> comments){
        this.comments = comments;
        return this;
//        return this.comments == comments ? this : new Board(this.boardId, this.title, this.content, this.writerId, this.writer, this.deleteYn, this.createdDate, this.modifiedDate, comments, this.user);
    }

    public Board withUser(User user){
        this.user = user;
        return this;
//        return this.user == user ? this : new Board(this.boardId, this.title, this.content, this.writerId, this.writer, this.deleteYn, this.createdDate, this.modifiedDate, this.comments, user);
    }

    @Builder
    public Board(Long boardId, String title, String content, Long writerId, String writer, String deleteYn, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.writer = writer;
        this.deleteYn = deleteYn;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
