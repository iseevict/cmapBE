package practiceProject.cmap.domain.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CommentWriteResponseDto {

        @NotNull
        Long commentId;
        @NotEmpty
        LocalDateTime createdAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CommentModifyResponseDto {

        @NotNull
        Long commentId;
        @NotEmpty
        LocalDateTime updatedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CommentDeleteResponseDto {

        @NotEmpty
        String message;
        @NotEmpty
        LocalDateTime deletedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardCommentListResponseDto {

        List<CommentDataDTO.CommentDataDto> commentDataDtoList;
        Boolean hasNext;
        Integer pageNum;
        Integer pageSize;
        Integer elementNum;
    }
}
