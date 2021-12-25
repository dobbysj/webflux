package com.webflux.www.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class BoardDTO {

    private Long boardId;

    private String title;

    private String content;

    private String writer;

    private String deleteYn = "N";

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public void updateBoard(BoardDTO boardDTO){
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
        this.writer = boardDTO.getWriter();
    }

    public Board toEntity(){
        return Board.builder()
                .boardId(boardId)
                .title(title)
                .content(content)
                .writer(writer)
                .deleteYn(deleteYn)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
    }

}
