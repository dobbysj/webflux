package com.webflux.www.repository;

import com.webflux.www.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BoardRepository extends R2dbcRepository<Board, Long> {

    Flux<Board> findAllByDeleteYn(Pageable pageable, String deleteYn);

    Flux<Board> findAllByDeleteYn(String deleteYn);

}
