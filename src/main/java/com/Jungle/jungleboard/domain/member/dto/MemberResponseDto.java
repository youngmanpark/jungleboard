package com.Jungle.jungleboard.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class READ {

        private Long userId;

        private String email;

        private String username;

        private String role;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class adminREAD {

        private Long userId;

        private String password;

        private String email;

        private String username;

        private String role;

    }
}
