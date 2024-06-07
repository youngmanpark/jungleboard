package com.Jungle.jungleboard.domain.member.service;

import com.Jungle.jungleboard.domain.member.dto.MemberRequestDto;
import com.Jungle.jungleboard.domain.member.dto.MemberResponseDto;
import com.Jungle.jungleboard.domain.member.entity.Member;
import com.Jungle.jungleboard.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.Jungle.jungleboard.global.common.Role.ROLE_USER;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void createMember(MemberRequestDto.CREATE create) {
        Optional<Member> member = memberRepository.findByEmailAndUsername(create.getEmail(), create.getUsername());
        if (member.isPresent())
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");


        memberRepository.save(new Member(create.getEmail(), create.getPassword(), create.getUsername(), ROLE_USER, "N"));
    }

    @Override
    public void updateMemberName(String email, MemberRequestDto.UPDATE update) {
        Member member = memberRepository.findMemberByEmailAndDelYn(email, "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (memberRepository.findByUsernameAndDelYn(update.getUsername(), "N").isPresent())
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");

        member.updateName(update);
    }

    @Override
    public void updateMemberPassword(String email, MemberRequestDto.UPDATE update) {
        Member member = memberRepository.findMemberByEmailAndDelYn(email, "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updateName(update);
    }

    @Override
    public void updateMemberRole(String email, MemberRequestDto.UPDATE update) {
        Member member = memberRepository.findMemberByEmailAndDelYn(email, "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updateName(update);
    }

    @Override
    public void deleteMember(MemberRequestDto.DELETE delete) {
        Member member = memberRepository.findByEmailAndDelYn(delete.getEmail(), "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!isPasswordMatch(member.getPassword(), delete.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        member.delete();
    }

    @Override
    public MemberResponseDto.READ getMember(MemberRequestDto.FIND find) {
        Member member = memberRepository.findByEmailAndDelYn(find.getEmail(), "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));

        return MemberResponseDto.READ.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .role(String.valueOf(member.getRole()))
                .build();
    }

    @Override
    public List<MemberResponseDto.READ> getAllMembers() {
        return memberRepository.findAllByDelYn("N").stream()
                .map(member -> MemberResponseDto.READ.builder()
                        .email(member.getEmail())
                        .username(member.getUsername())
                        .role(String.valueOf(member.getRole()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponseDto.READ login(MemberRequestDto.LOGIN login) {
        Member member = memberRepository.findMemberByEmailAndDelYn(login.getEmail(), "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!isPasswordMatch(member.getPassword(), login.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        return MemberResponseDto.READ.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .role(String.valueOf(member.getRole()))
                .accessToken("accessToken") //jwt 관련 구현시 변경
                .build();
    }


    @Override
    public MemberResponseDto.READ adminGetMember(Long userId) {
        return null;
    }

    @Override
    public List<MemberResponseDto.adminREAD> adminGetMembers(MemberRequestDto.CONDITION userRequestDto) {
        return null;
    }

    private boolean isPasswordMatch(String requestPassword, String getPassword) {
        return requestPassword.equals(getPassword);
    }
}
