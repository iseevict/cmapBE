package practiceProject.cmap.domain.board.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.config.s3.AmazonS3Manager;
import practiceProject.cmap.config.s3.Uuid;
import practiceProject.cmap.config.s3.UuidRepository;
import practiceProject.cmap.domain.board.converter.BoardConverter;
import practiceProject.cmap.domain.board.converter.BoardHashtagConverter;
import practiceProject.cmap.domain.board.dto.BoardDataDTO;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;
import practiceProject.cmap.domain.board.repository.BoardHashtagRepository;
import practiceProject.cmap.domain.board.repository.BoardImageRepository;
import practiceProject.cmap.domain.board.repository.BoardRepository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.repository.CafeRepository;
import practiceProject.cmap.domain.hashtag.entity.Hashtag;
import practiceProject.cmap.domain.hashtag.repository.HashtagRepository;
import practiceProject.cmap.domain.member.converter.MemberLikeBoardConverter;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.MemberRole;
import practiceProject.cmap.domain.member.entity.MemberStatus;
import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;
import practiceProject.cmap.domain.member.repository.MemberLikeBoardRepository;
import practiceProject.cmap.domain.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{

    private final MemberRepository memberRepository;
    private final CafeRepository cafeRepository;
    private final BoardRepository boardRepository;
    private final HashtagRepository hashtagRepository;
    private final BoardHashtagRepository boardHashtagRepository;
    private final MemberLikeBoardRepository memberLikeBoardRepository;
    private final AmazonS3Manager amazonS3Manager;
    private final UuidRepository uuidRepository;
    private final BoardImageRepository boardImageRepository;

    /**
     * 홈 화면 API
     * 반환 : List<Board>
     */
    @Override
    public List<Board> HomeRandomBoard() {

        Hashtag cmapHashtag = hashtagRepository.findById(1L).orElseThrow(() -> new CommonHandler(ErrorStatus._HASHTAG_NOT_FOUND));

        List<BoardHashtag> boardHashtagList = boardHashtagRepository.findAllByHashtag(cmapHashtag).get();

        List<Board> boardList = boardHashtagList.stream()
                .map(BoardHashtag::getBoard)
                .collect(Collectors.toList());


        if (boardList.size() > 1) {
            Collections.shuffle(boardList);

            List<Board> randomBoardList = new ArrayList<>();

            randomBoardList.add(boardList.get(0));
            randomBoardList.add(boardList.get(1));

            return randomBoardList;
        }
        else {
            return boardList;
        }
    }

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

        List<String> boardImageUrlList = param.getBoardPictureList().stream()
                .map(boardImageUrl -> {
                    String uuid = UUID.randomUUID().toString();
                    Uuid savedUuid = uuidRepository.save(
                            Uuid.builder()
                                    .uuid(uuid)
                                    .build()
                    );
                    return amazonS3Manager.uploadFile(amazonS3Manager.generateBoardKeyName(savedUuid), boardImageUrl);})
                .collect(Collectors.toList());

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

        Cafe findCafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        newBoard.setMember(writer);
        newBoard.setCafe(findCafe);
        boardImageRepository.saveAll(BoardConverter.toBoardImage(boardImageUrlList, newBoard));
        return boardRepository.save(newBoard);
    }

    /**
     * 게시판 수정 API
     * 반환 : Board
     */
    @Override
    @Transactional
    public Board BoardModify(@Valid BoardParameterDTO.BoardModifyParamDto param) {

        Member writer = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Board findBoard = boardRepository.findById(param.getBoardId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._BOARD_NOT_FOUND));

        if (!findBoard.getMember().equals(writer)) {
            throw new CommonHandler(ErrorStatus._NOT_MEMBERS_BOARD);
        }

        if (param.getHashtagList().stream()
                .anyMatch(hashtagId -> hashtagId.equals(1L))) {
            if (writer.getRole().equals(MemberRole.USER)) {
                throw new CommonHandler(ErrorStatus._FORBIDDEN_HASHTAG);
            }
        }

        List<Hashtag> hashtagList = param.getHashtagList().stream()
                .map(hashtagId -> hashtagRepository.findById(hashtagId)
                        .orElseThrow(() -> new CommonHandler(ErrorStatus._HASHTAG_NOT_FOUND)))
                .collect(Collectors.toList());

        List<BoardHashtag> newBoardHashtagList = BoardHashtagConverter.toBoardHashtagList(hashtagList);

        newBoardHashtagList.stream()
                .forEach(boardHashtag -> 
                        boardHashtag.setBoard(findBoard));

        // 원래 board - hashtag 연관관계 끊기
        List<BoardHashtag> boardHashtagList = boardHashtagRepository.findAllByBoard(findBoard).get();
        boardHashtagRepository.deleteAll(boardHashtagList);

        findBoard.modifyBoard(param.getTitle(), param.getBody(), newBoardHashtagList);

        return findBoard;
    }

    /**
     * 게시글 삭제 API
     * 반환 : Void
     */
    @Override
    @Transactional
    public void BoardDelete(@Valid BoardParameterDTO.BoardDeleteParamDto param) {

        Board findBoard = boardRepository.findById(param.getBoardId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._BOARD_NOT_FOUND));

        Member writer = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        if (!findBoard.getMember().equals(writer)) throw new CommonHandler(ErrorStatus._NOT_MEMBERS_BOARD);

        boardRepository.delete(findBoard);
    }

    /**
     * 게시글 하트 누르기 API
     * 반환 : MemberLikeBoard
     */
    @Override
    @Transactional
    public MemberLikeBoard BoardHeartOn(@Valid BoardParameterDTO.BoardHeartOnParamDto param) {

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Board findBoard = boardRepository.findById(param.getBoardId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._BOARD_NOT_FOUND));

        // 이미 있는 경우 삭제
        memberLikeBoardRepository.findByMemberAndBoard(findMember, findBoard).ifPresent(memberLikeBoardRepository::delete);

        MemberLikeBoard memberLikeBoard = MemberLikeBoardConverter.toMemberLikeBoard();
        memberLikeBoard.setMemberAndBoard(findMember, findBoard);

        return memberLikeBoardRepository.save(memberLikeBoard);
    }

    /**
     * 게시글 하트 삭제 API
     * 반환 : Void
     */
    @Override
    @Transactional
    public void BoardHeartOff(@Valid BoardParameterDTO.BoardHeartOffParamDto param) {

        Board findBoard = boardRepository.findById(param.getBoardId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._BOARD_NOT_FOUND));

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        MemberLikeBoard findMemberLikeBoard = memberLikeBoardRepository.findByMemberAndBoard(findMember, findBoard)
                .orElseThrow(() -> new CommonHandler(ErrorStatus._HEART_NOT_FOUND));

        memberLikeBoardRepository.delete(findMemberLikeBoard);
    }

    /**
     * 게시글 리스트 가져오기 API
     * 반환 : Page<Board>
     */
    @Override
    public Page<Board> BoardList(BoardParameterDTO.BoardListParamDto param) {

        return boardRepository.findAllForPage(PageRequest.of(param.getPage(), param.getSize()));
    }

    /**
     * 게시글마다 tagList 가져오기 함수
     * 반환 : List<BoardDataDTO.BoardDataDto>
     */
    @Override
    public List<BoardDataDTO.BoardDataDto> BoardTagList(Page<Board> boardPage) {

        return boardPage.stream()
                .map(board -> BoardDataDTO.BoardDataDto.builder()
                .title(board.getTitle())
                .body(board.getBody())
                .tagList(boardRepository.findAllTagByBoard(board))
                .memberId(board.getMember().getId())
                .memberName(board.getMember().getName())
                .cafeId(board.getCafe().getId())
                .cafeName(board.getCafe().getName())
                .build()
                ).collect(Collectors.toList());
    }

    /**
     * 게시글 리스트 가져오기 By Tag API
     * 반환 : Page<Board>
     */
    public Page<Board> BoardListByTag(BoardParameterDTO.BoardListByTagParamDto param) {

        return boardRepository.findAllForPageByTag(PageRequest.of(param.getPage(), param.getSize()), param.getTagList());
    }
    /**
     * 게시글 가져오기 API
     * 반환 : Board
     */
    @Override
    public Board BoardGet(BoardParameterDTO.BoardDataParamDto param) {

        Board findBoard = boardRepository.findById(param.getBoardId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._BOARD_NOT_FOUND));

        return findBoard;
    }
}
