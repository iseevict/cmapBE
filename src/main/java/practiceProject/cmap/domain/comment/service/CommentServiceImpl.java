package practiceProject.cmap.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.repository.BoardRepository;
import practiceProject.cmap.domain.comment.converter.CommentConverter;
import practiceProject.cmap.domain.comment.dto.CommentParameterDTO;
import practiceProject.cmap.domain.comment.entity.Comment;
import practiceProject.cmap.domain.comment.repository.CommentRepository;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 작성 API
     * 반환 : Comment
     */
    @Override
    @Transactional
    public Comment CommentWrite(CommentParameterDTO.CommentWriteParamDto param) {

        Member commentWriter = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Board findBoard = boardRepository.findById(param.getBoardId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._BOARD_NOT_FOUND));

        Comment newComment = CommentConverter.toComment(param);
        newComment.setMemberAndBoard(commentWriter, findBoard);

        return commentRepository.save(newComment);
    }
}
