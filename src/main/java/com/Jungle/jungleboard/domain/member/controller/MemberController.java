package com.Jungle.jungleboard.domain.member.controller;

import com.Jungle.jungleboard.domain.member.dto.MemberRequestDto;
import com.Jungle.jungleboard.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {


    private final MemberService memberService;

    @GetMapping("/hi")
    public String hi() {
        System.out.println("hi user");
        return "hi user";
    }

    @PostMapping("/join")
    public void createMember(@RequestBody MemberRequestDto.CREATE create) {
        memberService.createMember(create);
        System.out.println("회원가입 성공");
    }


}
