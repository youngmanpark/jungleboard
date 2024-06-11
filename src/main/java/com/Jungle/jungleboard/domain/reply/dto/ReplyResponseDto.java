package com.Jungle.jungleboard.domain.reply.dto;

import com.Jungle.jungleboard.domain.reply.entity.Reply;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReplyResponseDto {


    @Getter
    @Builder
    public static class READ {

        private Long replyId;

        private String username;

        private String content;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime updatedAt;

        public static READ of(Reply reply) {
            return READ.builder()
                    .replyId(reply.getId())
                    .username(reply.getMember().getUsername())
                    .content(reply.getContent())
                    .createdAt(reply.getCreatedAt())
                    .updatedAt(reply.getUpdatedAt())
                    .build();
        }


    }


}
