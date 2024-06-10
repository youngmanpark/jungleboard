package com.Jungle.jungleboard.domain.board.entity;

import com.Jungle.jungleboard.domain.board.dto.BoardRequestDto;
import com.Jungle.jungleboard.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "board_id")
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, name = "board_password")
    private String password;

    @Column(nullable = false, name = "del_yn")
    private String delYn;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Board(String title, String content, Member member, String password, String delYn) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.password = password;
        this.delYn = delYn;
        this.createdAt = LocalDateTime.now();
    }

    public void updateBoard(BoardRequestDto.B_UPDATE update) {
        this.title = update.getTitle();
        this.content = update.getContent();
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.delYn = "Y";
    }
}
