package com.Jungle.jungleboard.domain.board.service;

import com.Jungle.jungleboard.domain.board.dto.BoardRequestDto;
import com.Jungle.jungleboard.domain.board.dto.BoardResponseDto;
import com.Jungle.jungleboard.domain.board.entity.Board;
import com.Jungle.jungleboard.domain.board.repository.BoardRepository;
import com.Jungle.jungleboard.domain.member.entity.Member;
import com.Jungle.jungleboard.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createBoard(String username, BoardRequestDto.CREATE create) {

        Member member = memberRepository.findMemberByUsernameAndDelYn(username, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        boardRepository.save(
                new Board(create.getTitle(),
                        create.getContent(),
                        member,
                        bCryptPasswordEncoder.encode(create.getPassword()),
                        "N"));

    }

    @Override
    public void updateBoard(String username, Long id, BoardRequestDto.UPDATE update) {

        memberRepository.findMemberByUsernameAndDelYn(username, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Board board = boardRepository.findBoardByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        if (!bCryptPasswordEncoder.matches(update.getPassword(), board.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");


        board.updateBoard(update);

    }

    @Override
    public void deleteBoard(Long id, BoardRequestDto.DELETE delete) {
        Board board = boardRepository.findBoardByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        if (!bCryptPasswordEncoder.matches(delete.getPassword(), board.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");

        board.delete();
    }

    @Override
    public BoardResponseDto.READ getBoard(Long id) {
        Board board = boardRepository.findBoardByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return BoardResponseDto.READ.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getMember().getUsername())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    @Override
    public List<BoardResponseDto.READ> getAllBoards() {
        List<Board> boards = boardRepository.findAllByDelYn("N");

        return boards.stream()
                .map(board -> BoardResponseDto.READ.builder()
                        .boardId(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .username(board.getMember().getUsername())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .build())
                .toList();

    }
}
