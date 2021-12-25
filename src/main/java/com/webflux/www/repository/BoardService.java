package com.webflux.www.repository;

import com.webflux.www.domain.Board;
import com.webflux.www.domain.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    public Flux<Board> findAll(){
        return boardRepository.findAll();
    }

    public Flux<Board> findAllBy(){
        return boardRepository.findAllByDeleteYn("N");
    }

    public Mono<Page<Board>> findAllByPage(PageRequest pageRequest){
        return boardRepository.findAllByDeleteYn(pageRequest, "N")
                .collectList()
                .zipWith(boardRepository.count())
                .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()))
                ;
    }

    public Mono<Board> selectBoard(Long boardId){
        return boardRepository.findById(boardId);
    }

    public Mono<Board> insertBoard(BoardDTO boardDTO){
        return boardRepository.save(boardDTO.toEntity());
    }

    public Mono<Board> insertBoardFlatMap(BoardDTO boardDTO){
        return Mono.just(boardDTO.toEntity())
                .flatMap(boardRepository::save);
    }

    public Mono<Board> updateBoard(Long id, BoardDTO boardDTO){
        return Mono.just(id)
                .flatMap(boardRepository::findById)
                .map(item -> modelMapper.map(item, BoardDTO.class))
                .flatMap(item -> {
                    item.updateBoard(boardDTO);
                    return boardRepository.save(item.toEntity());
                })
                ;
/*
        return Mono.just(id)
                .flatMap(boardRepository::findById)
                .map(item -> modelMapper.map(item, BoardDTO.class))
                .map(item -> item.updateBoard(id, boardDTO.getTitle(), boardDTO.getContent()))
                .map(item -> item.toEntity())
                .flatMap(boardRepository::save)
                ;
*/
    }

}
