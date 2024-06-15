package practiceProject.cmap.domain.board.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.board.converter.BoardConverter;
import practiceProject.cmap.domain.board.converter.BoardHashtagConverter;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;
import practiceProject.cmap.domain.board.repository.BoardRepository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.repository.CafeRepository;
import practiceProject.cmap.domain.hashtag.entity.Hashtag;
import practiceProject.cmap.domain.hashtag.repository.HashtagRepository;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.MemberRole;
import practiceProject.cmap.domain.member.entity.MemberStatus;
import practiceProject.cmap.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{

    private final MemberRepository memberRepository;
    private final CafeRepository cafeRepository;
    private final BoardRepository boardRepository;
    private final HashtagRepository hashtagRepository;

    /**
     * 게시판 작성 API
     * 반환 : Board
     */
    @Override
    @Transactional
    public Board BoardWrite(@Valid BoardParameterDTO.BoardWriteParamDto param) {

        Member writer = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Board newBoard = BoardConverter.toBoard(param);

        // MANAGER만 CMAP 해시태그 가능
        if (param.getHashtagList().stream()
                .anyMatch(hashtagId -> hashtagId.equals(1L))) {
            if (writer.getRole().equals(MemberRole.USER)) {
                throw new CommonHandler(ErrorStatus._FORBIDDEN_HASHTAG);
            }
        }

        // 게시판 - 해시태그 연결
        List<Hashtag> hashtagList = param.getHashtagList().stream()
                .map(hashtagId -> hashtagRepository.findById(hashtagId)
                        .orElseThrow(() -> new CommonHandler(ErrorStatus._HASHTAG_NOT_FOUND))
                ).collect(Collectors.toList());

        List<BoardHashtag> boardHashtagList = BoardHashtagConverter.toBoardHashtagList(hashtagList);

        boardHashtagList.stream().forEach(boardHashtag ->
                boardHashtag.setBoard(newBoard));

        if (param.getCafeId() != null) {
            Cafe findCafe = cafeRepository.findById(param.getCafeId())
                    .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

            newBoard.setMember(writer);
            newBoard.setCafe(findCafe);
            return boardRepository.save(newBoard);
        }
        else {
            newBoard.setMember(writer);
            return boardRepository.save(newBoard);
        }
    }
}
