package com.Jungle.jungleboard.domain.member.service;

import com.Jungle.jungleboard.domain.member.dto.MemberRequestDto;
import com.Jungle.jungleboard.domain.member.dto.MemberResponseDto;

import java.util.List;

public interface MemberService {


    public void createMember(MemberRequestDto.CREATE create);

    public void updateMemberName(Long id, MemberRequestDto.UPDATE update);

    public void updateMemberPassword(Long id, MemberRequestDto.UPDATE update);

    public void updateMemberRole(Long id, MemberRequestDto.UPDATE update);

    public void deleteMember(Long id, MemberRequestDto.DELETE delete);

    public MemberResponseDto.READ getMember(MemberRequestDto.FIND find);

    public List<MemberResponseDto.READ> getAllMembers();

    public MemberResponseDto.READ login(MemberRequestDto.LOGIN userRequestDto);

    public MemberResponseDto.READ adminGetMember(Long userId);

    public List<MemberResponseDto.adminREAD> adminGetMembers(MemberRequestDto.CONDITION userRequestDto);
}
