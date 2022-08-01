package com.webflux.www.repository;

import com.webflux.www.domain.Board;
import com.webflux.www.domain.BoardDTO;
import com.webflux.www.domain.Comment;
import com.webflux.www.domain.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final CommentService commentService;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
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
        Mono<Board> boardMono =
                boardRepository.findById(boardId)
                        .flatMap(board -> Mono.just(board)
                                .zipWith(commentService.findAllByBoardId(boardId).collectList())
                                .map(t->t.getT1().withComments(t.getT2()))
                                .zipWith(userRepository.findByUserIdAndDeleteYn(board.getWriterId(), "N"))
                                .map(t->t.getT1().withUser(t.getT2()))
                        )
                ;
        return boardMono;
    }

    public Mono<User> selectUser(Long userId){
        return userRepository.findByUserIdAndDeleteYn(userId, "N");
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


//    public Mono<Tuple2<Board, User>> selectBoardtest(Long boardId){
//
//        Mono<Board> boardMono = boardRepository.findById(boardId);
//
//        Mono<Board> coco =
//                boardRepository.findById(boardId)
//                        .flatMap(board -> Mono.just(board)
//                                .zipWith(commentService.findAllByBoardId(boardId).collectList())
//                                .map(t->t.getT1().withComments(t.getT2()))
//                        );
//
//        Mono<Tuple2<User, List<Comment>>> userMono = boardMono
//                .flatMap(s->userRepository.findByUserIdAndDeleteYn(s.getWriterId(), "N")
//                        .zipWith(commentService.findAllByBoardId(s.getBoardId()).collectList()
//
//                        )
//                )
//                ;
//
//
//        Mono<Long> boardIdMono = boardMono.map(s->s.getBoardId());
//        Mono<Long> userIdMono = boardMono.map(s->s.getWriterId());
//
//
//        Mono<Tuple2<Board, User>> boardAndUser = boardMono
//                .zipWith(userIdMono.flatMap(s->userRepository.findByUserIdAndDeleteYn(s, "N")));
//        Mono<List<Comment>> commentMono = boardIdMono
//                .flatMap(s->commentService.findAllByBoardId(s).collectList())
//
//                ;
//        //comment에 user를 join해가지고,,,,,filter나 map으로 본글과 동일한 gender를 가진 글만 골라야될듯...
//        //그리고...
//
//
//
//        Mono<List<Comment>> comments = boardIdMono
//                .flatMap(s -> commentService.findAllByBoardId(s).collectList());
//
//        Mono<Tuple2<User, List<Comment>>> userAndComments = boardIdMono
//                .flatMap(s ->
//                        userRepository.findByUserIdAndDeleteYn(s, "N")
//                                .zipWith(commentService.findAllByBoardId(s).collectList())
//                )
//                ;
//
//        //조인을 어떻게할 것인가
//        //튜플로 어떻게 묶을 것인가
//        //성별이 같게? 어떻게 필터해? map으로..?
//
//        Mono.zip(boardMono, userAndComments);
////                boardMono
////                .map(s -> s.getWriterId())
////                .flatMap(s -> userRepository.findByUserIdAndDeleteYn(s, "N"));
//
//
//        return boardRepository.findById(boardId)
//                .zipWith(userRepository.findByUserIdAndDeleteYn(1L, "N"))
//                ;
//    }

}
