package com.Jungle.jungleboard.domain.member.repository;

import com.Jungle.jungleboard.domain.member.entity.Member;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {

    Optional<Member> findByEmailAndDelYn(String email, String delYn);

    Optional<Member> findByUsernameAndDelYn(String username, String delYn);

    Optional<Member> findMemberByEmailAndDelYn(String email, String n);

    List<Member> findAllByDelYn(String n);

    Optional<Member> findByEmailAndUsername(String email, String username);

    void save(Member member);


}
