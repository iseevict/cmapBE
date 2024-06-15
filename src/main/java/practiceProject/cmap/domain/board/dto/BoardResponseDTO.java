package practiceProject.cmap.domain.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BoardResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardWriteResponseDto {

        @NotNull
        Long boardId;
        @NotEmpty
        LocalDateTime createdAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardModifyResponseDto {

        @NotNull
        Long boardId;
        @NotEmpty
        LocalDateTime updatedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardDeleteResponseDto {

        @NotEmpty
        String message;
        @NotEmpty
        LocalDateTime deletedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardHeartOnResponseDto {

        @NotNull
        Long memberLikeBoardId;
        @NotEmpty
        LocalDateTime createdAt;
    }
}
