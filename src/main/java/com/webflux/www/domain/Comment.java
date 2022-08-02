package com.webflux.www.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table("Comment")
public class Comment extends Auditor {

    @Column("comment_id")
    private Long commentId;

    @Column("content")
    private String content;

    @Column("writer_id")
    private Long writerId;

    @Column("delete_yn")
    private String deleteYn;

    @Column("board_id")
    private Long boardId;

    public Comment(Long commentId, String content, Long writerId, String deleteYn, Long boardId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.commentId = commentId;
        this.content = content;
        this.writerId = writerId;
        this.deleteYn = deleteYn;
        this.boardId = boardId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
