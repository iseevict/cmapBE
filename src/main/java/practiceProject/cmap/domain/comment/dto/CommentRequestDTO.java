package practiceProject.cmap.domain.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class CommentRequestDTO {

    @Getter
    public static class CommentWriteRequestDto {

        @NotNull
        Long memberId;
        @NotEmpty
        String body;
    }
}
