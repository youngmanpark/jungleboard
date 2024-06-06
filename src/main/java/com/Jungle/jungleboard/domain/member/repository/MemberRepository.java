package com.Jungle.jungleboard.domain.member.repository;

import com.Jungle.jungleboard.domain.member.entity.Member;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

    Optional<Member> findByIdAndDelYn(Long id, String delYn);

    Optional<Member> findByEmailAndDelYn(String email, String delYn);

    Optional<Member> findByUsernameAndDelYn(String username, String delYn);

    boolean existsByEmailAndDelYn(String email, String delYn);

    boolean existsByUsernameAndDelYn(String username, String delYn);

    @NonNull
    Optional<Member> findById(@NonNull Long id);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsById(@NonNull Long id);


}
