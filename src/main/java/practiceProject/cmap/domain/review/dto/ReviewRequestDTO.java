package practiceProject.cmap.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewWriteRequestDto {

        @NotEmpty
        String title;
        @NotEmpty
        String body;
        @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
        Float score;
        @NotNull
        Long memberId;
    }

    @Getter
    public static class ReviewModifyRequestDto {

        @NotEmpty
        String title;
        @NotEmpty
        String body;
        @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
        Float score;
    }

}
