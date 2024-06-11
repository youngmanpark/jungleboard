package com.Jungle.jungleboard.domain.board.service;

import com.Jungle.jungleboard.domain.board.dto.BoardRequestDto;
import com.Jungle.jungleboard.domain.board.dto.BoardResponseDto;
import com.Jungle.jungleboard.global.common.Role;

import java.util.List;

public interface BoardService {

    BoardResponseDto.READ createBoard(String username, BoardRequestDto.B_CREATE create);

    public BoardResponseDto.READ updateBoard(String username, Role role, Long id, BoardRequestDto.B_UPDATE update);

    public void deleteBoard(Role role, Long id, BoardRequestDto.B_DELETE delete);

    public BoardResponseDto.READ getBoard(Long id);

    public List<BoardResponseDto.READ> getAllBoards();

}
