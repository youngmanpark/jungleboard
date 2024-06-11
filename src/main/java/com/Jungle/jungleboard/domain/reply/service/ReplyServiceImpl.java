package com.Jungle.jungleboard.domain.reply.service;

import com.Jungle.jungleboard.domain.board.entity.Board;
import com.Jungle.jungleboard.domain.board.repository.BoardRepository;
import com.Jungle.jungleboard.domain.member.entity.Member;
import com.Jungle.jungleboard.domain.member.repository.MemberRepository;
import com.Jungle.jungleboard.domain.reply.dto.ReplyRequestDto;
import com.Jungle.jungleboard.domain.reply.dto.ReplyResponseDto;
import com.Jungle.jungleboard.domain.reply.entity.Reply;
import com.Jungle.jungleboard.domain.reply.repository.ReplyRepository;
import com.Jungle.jungleboard.global.common.Role;
import com.Jungle.jungleboard.global.exception.NotFoundException;
import com.Jungle.jungleboard.global.exception.WrongPasswordException;
import com.Jungle.jungleboard.global.model.ResponseStatus;
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


    public ReplyResponseDto.READ createReply(String username, ReplyRequestDto.R_CREATE create) {
        Member member = memberRepository.findMemberByUsernameAndDelYn(username, "N")
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        Board board = boardRepository.findBoardByIdAndDelYn(create.getBoardId(), "N")
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_BOARD_NOT_FOUND));

        Reply reply = new Reply(member, board, create.getContent(),
                bCryptPasswordEncoder.encode(create.getPassword()), "N");

        replyRepository.save(reply);

        return ReplyResponseDto.READ.of(reply);
    }

    @Override
    public ReplyResponseDto.READ updateReply(String username, Role role, Long id, ReplyRequestDto.R_UPDATE update) {


        Member member = memberRepository.findMemberByUsernameAndDelYn(username, "N")
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        Reply reply = replyRepository.findReplyByIdAndDelYn(id, "N")
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_REPLY_NOT_FOUND));

        if (!isAuthorizedToUpdateOrDelete(role, reply, update.getPassword()))
            throw new WrongPasswordException(ResponseStatus.FAIL_BOARD_PASSWORD_NOT_MATCHED);

        reply.updateReply(update);

        return ReplyResponseDto.READ.of(reply);
    }

    @Override
    public void deleteReply(Role role, Long id, ReplyRequestDto.R_DELETE delete) {

        Reply reply = replyRepository.findReplyByIdAndDelYn(id, "N")
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_REPLY_NOT_FOUND));

        if (!isAuthorizedToUpdateOrDelete(role, reply, delete.getPassword()))
            throw new WrongPasswordException(ResponseStatus.FAIL_BOARD_PASSWORD_NOT_MATCHED);

        reply.delete();
    }

    private boolean isAuthorizedToUpdateOrDelete(Role role, Reply reply, String password) {
        if (!(role.equals(Role.ROLE_ADMIN))) {
            return bCryptPasswordEncoder.matches(password, reply.getPassword());
        }
        return true;
    }
}
