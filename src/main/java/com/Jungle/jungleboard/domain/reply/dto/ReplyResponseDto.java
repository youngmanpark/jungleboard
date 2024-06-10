package com.Jungle.jungleboard.domain.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReplyResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class READ {

        private Long replyId;

        private String username;

        private String content;

        private String password;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;


    }


}
