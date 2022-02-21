package com.webflux.www.repository;

import com.webflux.www.domain.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, Long> {

    Mono<User> findByUserIdAndDeleteYn(Long userId, String deleteYn);

}
