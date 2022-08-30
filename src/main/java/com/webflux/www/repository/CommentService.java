package com.webflux.www.repository;

import com.webflux.www.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Flux<Comment> findAllByBoardId(Long id){
        WebClient client = WebClient.builder().baseUrl("http://localhost:9090").build();
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/comments/").queryParam("boardId",id).build())
                .retrieve()
                .bodyToFlux(Comment.class)
                ;
    }

}
