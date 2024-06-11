package com.Jungle.jungleboard.domain.reply.service;

import com.Jungle.jungleboard.domain.reply.dto.ReplyRequestDto;
import com.Jungle.jungleboard.domain.reply.dto.ReplyResponseDto;
import com.Jungle.jungleboard.global.common.Role;

public interface ReplyService {
    public ReplyResponseDto.READ createReply(String username, ReplyRequestDto.R_CREATE create);

    public ReplyResponseDto.READ updateReply(String username, Role role, Long id, ReplyRequestDto.R_UPDATE update);

    public void deleteReply(Role role, Long id, ReplyRequestDto.R_DELETE delete);

}
