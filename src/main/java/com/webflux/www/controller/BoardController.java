package com.webflux.www.controller;

import com.webflux.www.domain.Board;
import com.webflux.www.domain.BoardDTO;
import com.webflux.www.domain.User;
import com.webflux.www.repository.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/board")
public class BoardController {

    private final BoardService boardService;

    //게시글 전체
    @RequestMapping
    public Flux<Board> getAll(){
        return boardService.findAllBy();
    }

    //게시글 전체 페이징
    @GetMapping("/page")
    public Mono<Page<Board>> getAllPage(@RequestParam("page") int page, @RequestParam("size") int size){
        return boardService.findAllByPage(PageRequest.of(page, size));
    }

    //게시글 상세
    @GetMapping("/{id}")
    public Mono<Board> boardDetail(@PathVariable long id) {
        return boardService.selectBoard(id);
    }

    @GetMapping("/test/{id}")
    public Mono<Board> userDetail(@PathVariable Long id) {
        return boardService.selectBoard(id);
    }

    //게시글 등록
    @PostMapping
    public Mono<Board> boardInsert(@RequestBody BoardDTO boardDTO){
        return boardService.insertBoard(boardDTO);
    }

    //게시글 등록2
    @PostMapping("/v2")
    public Mono<Board> boardInsertV2(@RequestBody BoardDTO boardDTO){
        return boardService.insertBoardFlatMap(boardDTO);
    }

    //게시글 수정
    @PutMapping("/{id}")
    public Mono<Board> boardUpdate(@PathVariable(name="id", required = true) Long id, @RequestBody BoardDTO boardDTO){
        return boardService.updateBoard(id, boardDTO);
    }


}
