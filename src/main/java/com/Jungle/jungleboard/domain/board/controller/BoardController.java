package com.Jungle.jungleboard.domain.board.controller;

import com.Jungle.jungleboard.domain.board.dto.BoardRequestDto;
import com.Jungle.jungleboard.domain.board.service.BoardService;
import com.Jungle.jungleboard.global.auth.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<String> createBoard(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody BoardRequestDto.B_CREATE create) {
        boardService.createBoard(userDetails.getUsername(), create);
        return ResponseEntity.ok("게시글이 작성되었습니다.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBoard(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody BoardRequestDto.B_UPDATE update) {
        boardService.updateBoard(userDetails.getUsername(), id, update);
        return ResponseEntity.ok("게시글이 수정되었습니다.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto.B_DELETE delete) {
        boardService.deleteBoard(id, delete);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoardDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getBoardAll() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

}
