package practiceProject.cmap.domain.comment.converter;

import practiceProject.cmap.domain.comment.dto.CommentParameterDTO;
import practiceProject.cmap.domain.comment.dto.CommentResponseDTO;
import practiceProject.cmap.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public class CommentConverter {

    public static CommentResponseDTO.CommentWriteResponseDto toCommentWriteResultDto(Comment comment) {

        return CommentResponseDTO.CommentWriteResponseDto.builder()
                .commentId(comment.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Comment toComment(CommentParameterDTO.CommentWriteParamDto param) {

        return Comment.builder()
                .body(param.getBody())
                .build();
    }
}
