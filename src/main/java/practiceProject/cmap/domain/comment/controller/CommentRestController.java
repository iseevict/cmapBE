package practiceProject.cmap.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.comment.converter.CommentConverter;
import practiceProject.cmap.domain.comment.converter.CommentDtoConverter;
import practiceProject.cmap.domain.comment.dto.CommentParameterDTO;
import practiceProject.cmap.domain.comment.dto.CommentRequestDTO;
import practiceProject.cmap.domain.comment.dto.CommentResponseDTO;
import practiceProject.cmap.domain.comment.entity.Comment;
import practiceProject.cmap.domain.comment.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    @Operation(summary = "댓글 작성 API", description = "댓글 작성 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BOARD1001", description = "게시글을 찾지 못했습니다.")
    })
    @Parameters({
            @Parameter(name = "boardId", description = "게시글 식별자, PathVariable")
    })
    public ApiResponse<CommentResponseDTO.CommentWriteResponseDto> CommentWrite(@RequestBody @Valid CommentRequestDTO.CommentWriteRequestDto request, @PathVariable("boardId") Long boardId) {

        CommentParameterDTO.CommentWriteParamDto commentWriteParamDto = CommentDtoConverter.INSTANCE.toCommentWriteParamDto(request, boardId);
        Comment comment = commentService.CommentWrite(commentWriteParamDto);
        return ApiResponse.onSuccess(CommentConverter.toCommentWriteResultDto(comment));
    }

    @PatchMapping("/boards/{boardId}/comments/{commentId}")
    @Operation(summary = "댓글 수정 API", description = "댓글 수정 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMENT1001", description = "댓글을 찾지 못했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMENT1002", description = "댓글 작성자가 아닙니다.")
    })
    @Parameters({
            @Parameter(name = "boardId", description = "게시글 식별자, PathVariable"),
            @Parameter(name = "commentId", description = "댓글 식별자, PathVariable")
    })
    public ApiResponse<CommentResponseDTO.CommentModifyResponseDto> CommentModify(@RequestBody @Valid CommentRequestDTO.CommentModifyRequestDto request, @PathVariable("commentId") Long commentId) {

        CommentParameterDTO.CommentModifyParamDto commentModifyParamDto = CommentDtoConverter.INSTANCE.toCommentModifyParamDto(request, commentId);
        Comment comment = commentService.CommentModify(commentModifyParamDto);
        return ApiResponse.onSuccess(CommentConverter.toCommentModifyResultDto(comment));
    }

    @DeleteMapping("/boards/{boardId}/comments/{commentId}")
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMENT1001", description = "댓글을 찾지 못했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMENT1002", description = "댓글 작성자가 아닙니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMENT1003", description = "해당 게시글의 댓글이 아닙니다.")
    })
    @Parameters({
            @Parameter(name = "boardId", description = "게시글 식별자, PathVariable"),
            @Parameter(name = "commentId", description = "댓글 식별자, PathVariable")
    })
    public ApiResponse<CommentResponseDTO.CommentDeleteResponseDto> CommentDelete(@RequestBody @Valid CommentRequestDTO.CommentDeleteRequestDto request, @PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId) {

        CommentParameterDTO.CommentDeleteParamDto commentDeleteParamDto = CommentDtoConverter.INSTANCE.toCommentDeleteParamDto(request, boardId, commentId);
        commentService.CommentDelete(commentDeleteParamDto);
        return ApiResponse.onSuccess(CommentConverter.toCommentDeleteResultDto());
    }
}
