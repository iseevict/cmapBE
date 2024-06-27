package practiceProject.cmap.domain.board.converter;

import org.springframework.data.domain.Page;
import practiceProject.cmap.domain.board.dto.BoardDataDTO;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.entity.BoardImage;
import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardConverter {

    public static BoardResponseDTO.BoardDataResponseDto toBoardDataResultDto(Board board) {

        List<Long> tagList = board.getBoardHashtagList().stream()
                .map(boardHashtag ->
                        boardHashtag.getHashtag().getId())
                .collect(Collectors.toList());

        return BoardResponseDTO.BoardDataResponseDto.builder()
                .title(board.getTitle())
                .body(board.getBody())
                .writer(board.getMember().getName())
                .memberId(board.getMember().getId())
                .likeNum(board.getMemberLikeBoardList().size())
                .updatedAt(board.getUpdatedAt())
                .tagList(tagList)
                .cafeId(board.getCafe().getId())
                .cafeName(board.getCafe().getName())
                .build();
    }

    public static BoardResponseDTO.BoardListByTagResponseDto toBoardListByTagResultDto(Page<Board> boardPage, List<BoardDataDTO.BoardDataDto> boardDataDtoList) {

        return BoardResponseDTO.BoardListByTagResponseDto.builder()
                .boardDataDtoList(boardDataDtoList)
                .listSize(boardDataDtoList.size())
                .totalPage(boardPage.getTotalPages())
                .totalElements(boardPage.getTotalElements())
                .isFirst(boardPage.isFirst())
                .isLast(boardPage.isLast())
                .build();
    }

    public static BoardResponseDTO.BoardListResponseDto toBoardListResultDto(Page<Board> boardPage, List<BoardDataDTO.BoardDataDto> boardDataDtoList) {

        return BoardResponseDTO.BoardListResponseDto.builder()
                .boardDataDtoList(boardDataDtoList)
                .listSize(boardDataDtoList.size())
                .totalPage(boardPage.getTotalPages())
                .totalElements(boardPage.getTotalElements())
                .isFirst(boardPage.isFirst())
                .isLast(boardPage.isLast())
                .build();
    }

    public static BoardResponseDTO.HomeRandomBoardResponseDto toHomeRandomBoardResultDto(List<Board> boardList) {

        return BoardResponseDTO.HomeRandomBoardResponseDto.builder()
                .firstBoard(BoardResponseDTO.RandomBoardResponseDto.builder()
                        .boardId(boardList.get(0).getId())
                        .cafeName(boardList.get(0).getCafe().getName())
                        .build())
                .secondBoard(BoardResponseDTO.RandomBoardResponseDto.builder()
                        .boardId(boardList.get(1).getId())
                        .cafeName(boardList.get(1).getCafe().getName())
                        .build())
                .build();
    }

    public static BoardResponseDTO.BoardWriteResponseDto toBoardWriteResultDto(Board board) {

        return BoardResponseDTO.BoardWriteResponseDto.builder()
                .boardId(board.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static BoardResponseDTO.BoardModifyResponseDto toBoardModifyResultDto(Board board) {

        return BoardResponseDTO.BoardModifyResponseDto.builder()
                .boardId(board.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static BoardResponseDTO.BoardDeleteResponseDto toBoardDeleteResultDto() {

        return BoardResponseDTO.BoardDeleteResponseDto.builder()
                .message("삭제 성공입니다.")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static BoardResponseDTO.BoardHeartOnResponseDto toBoardHeartOnResultDto(MemberLikeBoard memberLikeBoard) {

        return BoardResponseDTO.BoardHeartOnResponseDto.builder()
                .memberLikeBoardId(memberLikeBoard.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static BoardResponseDTO.BoardHeartOffResponseDto toBoardHeartOffResultDto() {

        return BoardResponseDTO.BoardHeartOffResponseDto.builder()
                .message("삭제 성공입니다.")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static Board toBoard(BoardParameterDTO.BoardWriteParamDto param) {

        return Board.builder()
                .title(param.getTitle())
                .body(param.getBody())
                .boardHashtagList(new ArrayList<>())
                .build();
    }

    public static List<BoardImage> toBoardImage(List<String> imageUrlList, Board board) {

        List<BoardImage> boardImageList = imageUrlList.stream()
                .map(imageUrl ->
                        BoardImage.builder()
                                .boardImageUrl(imageUrl)
                                .board(board)
                                .build()
                ).collect(Collectors.toList());

        return boardImageList;
    }
}
