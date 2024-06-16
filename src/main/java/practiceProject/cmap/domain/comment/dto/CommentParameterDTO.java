package practiceProject.cmap.domain.comment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CommentWriteParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long boardId;
        @NotEmpty
        String body;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CommentModifyParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long commentId;
        @NotNull
        Long boardId;
        @NotEmpty
        String body;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CommentDeleteParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long commentId;
        @NotNull
        Long boardId;
    }
}
