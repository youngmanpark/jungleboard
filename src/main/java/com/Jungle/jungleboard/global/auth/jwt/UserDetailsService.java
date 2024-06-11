package com.Jungle.jungleboard.global.auth.jwt;

import com.Jungle.jungleboard.domain.member.entity.Member;
import com.Jungle.jungleboard.domain.member.repository.MemberRepository;
import com.Jungle.jungleboard.global.exception.NotFoundException;
import com.Jungle.jungleboard.global.model.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsernameAndDelYn(username, "N")
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        return new CustomUserDetails(member);
    }
}
