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

    @PutMapping("/update/name/{id}")
    public ResponseEntity<String> updateMemberName(@PathVariable Long id, @RequestBody MemberRequestDto.UPDATE update) {
        memberService.updateMemberName(id, update);
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    @PutMapping("/update/password/{id}")
    public ResponseEntity<String> updateMemberPassword(@PathVariable Long id, @RequestBody MemberRequestDto.UPDATE update) {
        memberService.updateMemberPassword(id, update);
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    @PutMapping("/update/role/{id}")
    public ResponseEntity<String> updateMemberRole(@PathVariable Long id, @RequestBody MemberRequestDto.UPDATE update) {
        memberService.updateMemberRole(id, update);
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id, @RequestBody MemberRequestDto.DELETE delete) {
        memberService.deleteMember(id, delete);
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
