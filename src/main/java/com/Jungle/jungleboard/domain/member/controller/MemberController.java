package com.Jungle.jungleboard.domain.member.controller;

import com.Jungle.jungleboard.domain.member.dto.MemberRequestDto;
import com.Jungle.jungleboard.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {


    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> createMember(@RequestBody MemberRequestDto.CREATE create) {
        memberService.createMember(create);
        return ResponseEntity.ok("회원 가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDto.LOGIN login) {
        return ResponseEntity.ok(memberService.login(login));
    }

    @PutMapping("/update/name/{email}")
    public ResponseEntity<String> updateMemberName(@PathVariable String email, @RequestBody MemberRequestDto.UPDATE update) {
        memberService.updateMemberName(email, update);
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    @PutMapping("/update/password/{email}")
    public ResponseEntity<String> updateMemberPassword(@PathVariable String email, @RequestBody MemberRequestDto.UPDATE update) {
        memberService.updateMemberPassword(email, update);
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    @PutMapping("/update/role/{email}")
    public ResponseEntity<String> updateMemberRole(@PathVariable String email, @RequestBody MemberRequestDto.UPDATE update) {
        memberService.updateMemberRole(email, update);
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteMember(@PathVariable String email, @RequestBody MemberRequestDto.DELETE delete) {
        memberService.deleteMember(email, delete);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getMemberDetailByEmail(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMember(new MemberRequestDto.FIND(email)));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getMemberAll() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

}
