package com.Jungle.jungleboard.domain.board.service;

import com.Jungle.jungleboard.domain.board.dto.BoardRequestDto;
import com.Jungle.jungleboard.domain.board.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {

    void createBoard(String username, BoardRequestDto.CREATE create);

    public void updateBoard(String username, Long id, BoardRequestDto.UPDATE update);

    public void deleteBoard(Long id, BoardRequestDto.DELETE delete);

    public BoardResponseDto.READ getBoard(Long id);

    public List<BoardResponseDto.READ> getAllBoards();

}
