package practiceProject.cmap.domain.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CommentWriteResponseDto {

        @NotNull
        Long commentId;
        @NotEmpty
        LocalDateTime createdAt;
    }
}
