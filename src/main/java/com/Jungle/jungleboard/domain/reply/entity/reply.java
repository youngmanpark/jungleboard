package com.Jungle.jungleboard.domain.reply.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private String delYn;

}
