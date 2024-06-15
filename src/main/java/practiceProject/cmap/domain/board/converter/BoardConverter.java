package practiceProject.cmap.domain.board.converter;

import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardConverter {

    public static BoardResponseDTO.BoardWriteResponseDto toBoardWriteResultDto(Board board) {

        return BoardResponseDTO.BoardWriteResponseDto.builder()
                .boardId(board.getId())
                .createdAt(LocalDateTime.now())
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
