package practiceProject.cmap.domain.review.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewWriteResponseDto {

        @NotNull
        Long reviewId;
        @NotEmpty
        LocalDateTime createdAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewModifyResponseDto {

        @NotNull
        Long reviewId;
        @NotEmpty
        LocalDateTime updatedAt;
    }
}
