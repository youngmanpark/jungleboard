package com.Jungle.jungleboard.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberFindRepository {

    private final JPAQueryFactory queryFactory;



}
