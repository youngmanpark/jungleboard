package com.Jungle.jungleboard.domain.member.service;

import com.Jungle.jungleboard.domain.member.dto.MemberRequestDto;
import com.Jungle.jungleboard.domain.member.dto.MemberResponseDto;

import java.util.List;

public interface MemberService {


    public void createMember(MemberRequestDto.CREATE create);

    public void updateMember(MemberRequestDto.UPDATE update);

    public void deleteMember(Long userId);

    public MemberResponseDto.READ getMember(Long userId);

    public List<MemberResponseDto.READ> getMember(MemberRequestDto.CONDITION condition);

    public MemberResponseDto.READ login(MemberRequestDto.LOGIN userRequestDto);

    public MemberResponseDto.READ adminGetMember(Long userId);

    public List<MemberResponseDto.adminREAD> adminGetMembers(MemberRequestDto.CONDITION userRequestDto);
}
