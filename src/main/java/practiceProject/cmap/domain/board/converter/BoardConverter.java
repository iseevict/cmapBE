package practiceProject.cmap.domain.board.converter;

import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardConverter {

    public static BoardResponseDTO.HomeRandomBoardResponseDto toHomeRandomBoardResultDto(List<Board> boardList) {

        return BoardResponseDTO.HomeRandomBoardResponseDto.builder()
                .firstBoard(BoardResponseDTO.RandomBoardDataDto.builder()
                        .boardId(boardList.get(0).getId())
                        .cafeName(boardList.get(0).getCafe().getName())
                        .build())
                .secondBoard(BoardResponseDTO.RandomBoardDataDto.builder()
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
}
