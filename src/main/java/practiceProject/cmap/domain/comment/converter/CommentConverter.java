package practiceProject.cmap.domain.comment.converter;

import org.springframework.data.domain.Slice;
import practiceProject.cmap.domain.comment.dto.CommentDataDTO;
import practiceProject.cmap.domain.comment.dto.CommentParameterDTO;
import practiceProject.cmap.domain.comment.dto.CommentResponseDTO;
import practiceProject.cmap.domain.comment.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentConverter {

    public static CommentResponseDTO.BoardCommentListResponseDto toBoardCommentListResultDto(Slice<Comment> commentSlice) {

        List<CommentDataDTO.CommentDataDto> commentDataDtoList = commentSlice.stream()
                .map(comment ->
                        CommentDataDTO.CommentDataDto.builder()
                                .body(comment.getBody())
                                .writer(comment.getMember().getName())
                                .memberId(comment.getMember().getId())
                                .updatedAt(comment.getUpdatedAt())
                                .build()
                ).collect(Collectors.toList());

        return CommentResponseDTO.BoardCommentListResponseDto.builder()
                .commentDataDtoList(commentDataDtoList)
                .hasNext(commentSlice.hasNext())
                .pageNum(commentSlice.getNumber())
                .pageSize(commentSlice.getSize())
                .elementNum(commentSlice.getNumberOfElements())
                .build();
    }

    public static CommentResponseDTO.CommentWriteResponseDto toCommentWriteResultDto(Comment comment) {

        return CommentResponseDTO.CommentWriteResponseDto.builder()
                .commentId(comment.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CommentResponseDTO.CommentModifyResponseDto toCommentModifyResultDto(Comment comment) {

        return CommentResponseDTO.CommentModifyResponseDto.builder()
                .commentId(comment.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CommentResponseDTO.CommentDeleteResponseDto toCommentDeleteResultDto() {

        return CommentResponseDTO.CommentDeleteResponseDto.builder()
                .message("삭제 성공입니다.")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static Comment toComment(CommentParameterDTO.CommentWriteParamDto param) {

        return Comment.builder()
                .body(param.getBody())
                .build();
    }
}
