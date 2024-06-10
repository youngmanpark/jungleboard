package com.Jungle.jungleboard.domain.reply.service;

import com.Jungle.jungleboard.domain.board.entity.Board;
import com.Jungle.jungleboard.domain.board.repository.BoardRepository;
import com.Jungle.jungleboard.domain.member.entity.Member;
import com.Jungle.jungleboard.domain.member.repository.MemberRepository;
import com.Jungle.jungleboard.domain.reply.dto.ReplyRequestDto;
import com.Jungle.jungleboard.domain.reply.entity.Reply;
import com.Jungle.jungleboard.domain.reply.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void createReply(String username, ReplyRequestDto.R_CREATE create) {
        Member member = memberRepository.findMemberByUsernameAndDelYn(username, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Board board = boardRepository.findBoardByIdAndDelYn(create.getBoardId(), "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        replyRepository.save(
                new Reply(member,
                        board,
                        create.getContent(),
                        "N",
                        bCryptPasswordEncoder.encode(create.getPassword())));
    }

    @Override
    public void updateReply(String username, Long id, ReplyRequestDto.R_UPDATE update) {


        Member member = memberRepository.findMemberByUsernameAndDelYn(username, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Reply reply = replyRepository.findReplyByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        if (!bCryptPasswordEncoder.matches(update.getPassword(), reply.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");

        reply.updateReply(update);
    }

    @Override
    public void deleteReply(Long id, ReplyRequestDto.R_DELETE delete) {

        Reply reply = replyRepository.findReplyByIdAndDelYn(id, "N")
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        if (!bCryptPasswordEncoder.matches(delete.getPassword(), reply.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");

        reply.delete();
    }
}
