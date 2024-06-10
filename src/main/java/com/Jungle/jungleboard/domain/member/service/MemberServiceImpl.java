package com.Jungle.jungleboard.domain.member.service;

import com.Jungle.jungleboard.domain.member.dto.MemberRequestDto;
import com.Jungle.jungleboard.domain.member.dto.MemberResponseDto;
import com.Jungle.jungleboard.domain.member.entity.Member;
import com.Jungle.jungleboard.domain.member.repository.MemberRepository;
import com.Jungle.jungleboard.global.auth.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void createMember(MemberRequestDto.M_CREATE create) {
        Optional<Member> member = memberRepository.findByEmailAndUsername(create.getEmail(), create.getUsername());
        if (member.isPresent())
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");


        memberRepository.save(new Member(create.getEmail(), bCryptPasswordEncoder.encode(create.getPassword()), create.getUsername(), ROLE_USER, "N"));
    }

    @Override
    public void updateMemberName(Long id, MemberRequestDto.M_UPDATE update) {
        Member member = memberRepository.findMemberByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (memberRepository.findByUsernameAndDelYn(update.getUsername(), "N").isPresent())
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");

        member.updateName(update);
    }

    @Override
    public void updateMemberPassword(Long id, MemberRequestDto.M_UPDATE update) {
        Member member = memberRepository.findMemberByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updatePassword(update);
    }

    @Override
    public void updateMemberRole(Long id, MemberRequestDto.M_UPDATE update) {
        Member member = memberRepository.findMemberByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updateRole(update);
    }

    @Override
    public void deleteMember(Long id, MemberRequestDto.M_DELETE delete) {
        Member member = memberRepository.findMemberByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!bCryptPasswordEncoder.matches(delete.getPassword(), member.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        member.delete();
    }

    @Override
    public MemberResponseDto.READ getMember(MemberRequestDto.M_FIND find) {
        Member member = memberRepository.findByEmailAndDelYn(find.getEmail(), "N")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));

        return MemberResponseDto.READ.builder()
                .userId(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .role(String.valueOf(member.getRole()))
                .build();
    }

    @Override
    public List<MemberResponseDto.READ> getAllMembers() {
        return memberRepository.findAllByDelYn("N").stream()
                .map(member -> MemberResponseDto.READ.builder()
                        .userId(member.getId())
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

        if (!bCryptPasswordEncoder.matches(login.getPassword(), member.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        String token = JwtProvider.generateToken(member.getEmail(), member.getRole());

        return MemberResponseDto.READ.builder()
                .userId(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .role(String.valueOf(member.getRole()))
                .accessToken(token)
                .build();
    }


    @Override
    public MemberResponseDto.READ adminGetMember(Long userId) {
        return null;
    }

    @Override
    public List<MemberResponseDto.adminREAD> adminGetMembers(MemberRequestDto.M_CONDITION userRequestDto) {
        return null;
    }

}
