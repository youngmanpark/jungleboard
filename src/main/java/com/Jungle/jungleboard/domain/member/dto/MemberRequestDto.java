package com.Jungle.jungleboard.domain.member.dto;

import com.Jungle.jungleboard.global.common.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDto {

    @Getter
    @NoArgsConstructor
    public static class CREATE {

        private String email;

        private String password;

        private String username;

        private Role role;

    }

    @Getter
    @NoArgsConstructor
    public static class UPDATE {

        private String password;

        private String username;

        private String role;

    }

    @Getter
    @NoArgsConstructor
    public static class CONDITION {

        private String email;

        private String username;

        private String role;
    }

    @Getter
    @NoArgsConstructor
    public static class LOGIN {

        private String email;

        private String password;

    }

    @Getter
    @NoArgsConstructor
    public static class DELETE {

        private String email;

        private String password;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FIND {

        private String email;

    }
}
