package com.webflux.www;

import com.webflux.www.domain.Comment;
import com.webflux.www.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class RemoteApplication {

    @Slf4j
    @RestController
    @RequiredArgsConstructor
    public static class remoteController {

        private final CommentRepository commentRepository;

        @GetMapping("/comments")
        public Flux<Comment> getComments(Long boardId){
            log.info("boardId : {}", boardId);
            return commentRepository.findAllByBoardId(boardId);
        }
    }

    public static void main(String[] args) {
        System.setProperty("SERVER_PORT", "9090");
        SpringApplication.run(RemoteApplication.class, args);
    }
}
