package com.webflux.www.repository;

import com.webflux.www.domain.Comment;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CommentRepository extends R2dbcRepository<Comment, Long> {

    Flux<Comment> findAllByBoardId(Long boardId);

}
