package com.Jungle.jungleboard.domain.reply.service;

import com.Jungle.jungleboard.domain.reply.dto.ReplyRequestDto;

public interface ReplyService {
    public void createReply(String username, ReplyRequestDto.R_CREATE create);

    public void updateReply(String username, Long id, ReplyRequestDto.R_UPDATE update);

    public void deleteReply(Long id, ReplyRequestDto.R_DELETE delete);

}
