package practiceProject.cmap.domain.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.board.converter.BoardConverter;
import practiceProject.cmap.domain.board.converter.BoardDtoConverter;
import practiceProject.cmap.domain.board.dto.BoardDataDTO;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardRequestDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.service.BoardService;
import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping("/")
    @Operation(summary = "홈 화면 API", description = "홈 화면 API 입니다. 랜덤 공지 2개 가져옵니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<BoardResponseDTO.HomeRandomBoardResponseDto> HomeRandomBoard() {

        List<Board> boardList = boardService.HomeRandomBoard();
        return ApiResponse.onSuccess(BoardConverter.toHomeRandomBoardResultDto(boardList));
    }

    @PostMapping("/boards")
    @Operation(summary = "게시글 작성 API", description = "게시글 작성 API 입니다.")
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

    @PatchMapping("/boards/{boardId}")
    @Operation(summary = "게시글 수정 API", description = "게시글 수정 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG1001", description = "태그를 찾지 못했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TAG1002", description = "권한이 없습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOARD1001", description = "게시글을 찾지 못했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOARD1002", description = "게시글 작성자가 아닙니다.")
    })
    @Parameters({
            @Parameter(name = "boardId", description = "게시글 식별자, PathVariable")
    })
    public ApiResponse<BoardResponseDTO.BoardModifyResponseDto> BoardModify(@RequestBody @Valid BoardRequestDTO.BoardModifyRequestDto request, @PathVariable("boardId") Long boardId) {

        BoardParameterDTO.BoardModifyParamDto boardModifyParamDto = BoardDtoConverter.INSTANCE.toBoardModifyParamDto(request, boardId);
        Board board = boardService.BoardModify(boardModifyParamDto);
        return ApiResponse.onSuccess(BoardConverter.toBoardModifyResultDto(board));
    }

    @DeleteMapping("/boards/{boardId}")
    @Operation(summary = "게시글 삭제 API", description = "게시글 삭제 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOARD1001", description = "게시글을 찾지 못했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOARD1002", description = "게시글 작성자가 아닙니다.")
    })
    @Parameters({
            @Parameter(name = "boardId", description = "게시글 식별자, PathVariable")
    })
    public ApiResponse<BoardResponseDTO.BoardDeleteResponseDto> BoardDelete(@RequestBody @Valid BoardRequestDTO.BoardDeleteRequestDto request, @PathVariable("boardId") Long boardId) {

        BoardParameterDTO.BoardDeleteParamDto boardDeleteParamDto = BoardDtoConverter.INSTANCE.toBoardDeleteParamDto(request, boardId);
        boardService.BoardDelete(boardDeleteParamDto);
        return ApiResponse.onSuccess(BoardConverter.toBoardDeleteResultDto());
    }

    @PostMapping("/boards/{boardId}")
    @Operation(summary = "게시글 하트 누르기 API", description = "게시글 하트 누르기 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOARD1001", description = "게시글을 찾지 못했습니다.")
    })
    @Parameters({
            @Parameter(name = "boardId", description = "게시글 식별자, PathVariable")
    })
    public ApiResponse<BoardResponseDTO.BoardHeartOnResponseDto> BoardHeartOn(@RequestBody @Valid BoardRequestDTO.BoardHeartOnRequestDto request, @PathVariable("boardId") Long boardId) {

        BoardParameterDTO.BoardHeartOnParamDto boardHeartOnParamDto = BoardDtoConverter.INSTANCE.toBoardHeartOnParamDto(request, boardId);
        MemberLikeBoard memberLikeBoard = boardService.BoardHeartOn(boardHeartOnParamDto);
        return ApiResponse.onSuccess(BoardConverter.toBoardHeartOnResultDto(memberLikeBoard));
    }

    @DeleteMapping("/{memberId}/boards/{boardId}")
    @Operation(summary = "게시글 하트 삭제 API", description = "게시글 하트 삭제 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "HEART1001", description = "이미 좋아요를 누르지 않은 상태입니다.")
    })
    @Parameters({
            @Parameter(name = "boardId", description = "게시글 식별자, PathVariable"),
            @Parameter(name = "memberId", description = "멤버 식별자, PathVariable")
    })
    public ApiResponse<BoardResponseDTO.BoardHeartOffResponseDto> BoardHeartOff(@PathVariable("memberId") Long memberId, @PathVariable("boardId") Long boardId) {

        BoardParameterDTO.BoardHeartOffParamDto boardHeartOffParamDto = BoardDtoConverter.INSTANCE.toBoardHeartOffParamDto(memberId, boardId);
        boardService.BoardHeartOff(boardHeartOffParamDto);
        return ApiResponse.onSuccess(BoardConverter.toBoardHeartOffResultDto());
    }

    @GetMapping("/boards/default")
    @Operation(summary = "게시글 리스트 가져오기 API", description = "게시글 리스트 가져오기 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<BoardResponseDTO.BoardListResponseDto> BoardList(@RequestParam Integer page, @RequestParam Integer size) {

        BoardParameterDTO.BoardListParamDto boardListParamDto = BoardDtoConverter.INSTANCE.toBoardListParamDto(page, size);
        Page<Board> boardPage = boardService.BoardList(boardListParamDto);
        List<BoardDataDTO.BoardDataDto> boardDataDtoList = boardService.BoardTagList(boardPage);
        return ApiResponse.onSuccess(BoardConverter.toBoardListResultDto(boardPage, boardDataDtoList));
    }

    @GetMapping("/boards")
    @Operation(summary = "게시글 리스트 가져오기 By Tag API", description = "게시글 리스트 가져오기 By Tag API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<BoardResponseDTO.BoardListByTagResponseDto> BoardListByTag(@RequestParam Integer page,
                                                                                  @RequestParam Integer size,
                                                                                  @RequestParam List<Long> tagList) {

        if (tagList.isEmpty()) tagList = new ArrayList<>();
        BoardParameterDTO.BoardListByTagParamDto boardListByTagParamDto = BoardDtoConverter.INSTANCE.toBoardListByTagParamDto(page, size, tagList);
        Page<Board> boardPage = boardService.BoardListByTag(boardListByTagParamDto);
        List<BoardDataDTO.BoardDataDto> boardDataDtoList = boardService.BoardTagList(boardPage);
        return ApiResponse.onSuccess(BoardConverter.toBoardListByTagResultDto(boardPage, boardDataDtoList));
    }
}
