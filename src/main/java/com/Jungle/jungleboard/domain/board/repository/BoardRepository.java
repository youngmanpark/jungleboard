package com.Jungle.jungleboard.domain.board.repository;

import com.Jungle.jungleboard.domain.board.entity.Board;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends Repository<Board, Long> {

    Optional<Board> findBoardByIdAndDelYn(Long id, String delYn);

    void save(Board board);

    List<Board> findAllByDelYn(String delYn);
}
