package practiceProject.cmap.domain.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.board.converter.BoardConverter;
import practiceProject.cmap.domain.board.converter.BoardDtoConverter;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardRequestDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/boards")
    @Operation(summary = "게시판 작성 API", description = "게시판 작성 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG1001", description = "태그를 찾지 못했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG1002", description = "권한이 없습니다.")
    })
    public ApiResponse<BoardResponseDTO.BoardWriteResponseDto> BoardWrite(@RequestBody @Valid BoardRequestDTO.BoardWriteRequestDto request) {
        BoardParameterDTO.BoardWriteParamDto boardWriteParamDto = BoardDtoConverter.INSTANCE.toBoardWriteParamDto(request);
        Board board = boardService.BoardWrite(boardWriteParamDto);
        return ApiResponse.onSuccess(BoardConverter.toBoardWriteResultDto(board));
    }

}
