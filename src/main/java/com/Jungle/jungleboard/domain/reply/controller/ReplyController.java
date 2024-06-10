package com.Jungle.jungleboard.domain.reply.controller;

import com.Jungle.jungleboard.domain.reply.dto.ReplyRequestDto;
import com.Jungle.jungleboard.domain.reply.service.ReplyService;
import com.Jungle.jungleboard.global.auth.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replys")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/create")
    public ResponseEntity<String> createReply(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody ReplyRequestDto.R_CREATE create) {
        replyService.createReply(userDetails.getUsername(), create);
        return ResponseEntity.ok("댓글이 작성되었습니다.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateReply(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody ReplyRequestDto.R_UPDATE update) {
        replyService.updateReply(userDetails.getUsername(), id, update);
        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReply(@PathVariable Long id, @RequestBody ReplyRequestDto.R_DELETE delete) {
        replyService.deleteReply(id, delete);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
