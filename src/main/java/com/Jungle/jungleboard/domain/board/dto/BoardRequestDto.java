package com.Jungle.jungleboard.domain.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardRequestDto {
    @Getter
    @NoArgsConstructor
    public static class CREATE {

        private String title;

        private String content;

        private String password;

        private String username;

    }

    @Getter
    @NoArgsConstructor
    public static class UPDATE {

        private Long id;

        private String title;

        private String content;

        private String password;

    }

    @Getter
    @NoArgsConstructor
    public static class CONDITION {

        private String title;

        private String content;

        private String username;
    }

    @Getter
    @NoArgsConstructor
    public static class DELETE {

        private Long boardId;

        private String password;

    }

}
