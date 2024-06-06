package com.Jungle.jungleboard.domain.member.service;

import com.Jungle.jungleboard.domain.member.dto.MemberRequestDto;
import com.Jungle.jungleboard.domain.member.dto.MemberResponseDto;
import com.Jungle.jungleboard.domain.member.entity.Member;
import com.Jungle.jungleboard.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.Jungle.jungleboard.global.common.Role.ROLE_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void createMember(MemberRequestDto.CREATE create) {

        isExistEmail(create.getEmail());
        isExistUsername(create.getUsername());
        Member member = Member.builder()
                .email(create.getEmail())
                .password(create.getPassword())
                .username(create.getUsername())
                .role(ROLE_USER)
                .delYn("N")
                .build();
        memberRepository.save(member);
    }

    private void isExistUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }

    private void isExistEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

    @Override
    public void updateMember(MemberRequestDto.UPDATE update) {

    }

    @Override
    public void deleteMember(Long userId) {

    }

    @Override
    public MemberResponseDto.READ getMember(Long userId) {
        return null;
    }

    @Override
    public List<MemberResponseDto.READ> getMember(MemberRequestDto.CONDITION condition) {


        return null;
    }

    @Override
    public MemberResponseDto.READ login(MemberRequestDto.LOGIN userRequestDto) {
        return null;
    }

    @Override
    public MemberResponseDto.READ adminGetMember(Long userId) {
        return null;
    }

    @Override
    public List<MemberResponseDto.adminREAD> adminGetMembers(MemberRequestDto.CONDITION userRequestDto) {
        return null;
    }
}
