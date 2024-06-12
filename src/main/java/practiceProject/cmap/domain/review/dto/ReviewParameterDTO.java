package practiceProject.cmap.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewWriteParamDto {

        @NotNull
        Long cafeId;
        @NotEmpty
        String title;
        @NotEmpty
        String body;
        @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
        Float score;
        @NotNull
        Long memberId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewModifyParamDto {

        @NotNull
        Long cafeId;
        @NotNull
        Long reviewId;
        @NotEmpty
        String title;
        @NotEmpty
        String body;
        @DecimalMin(value = "0.0") @DecimalMax("5.0")
        Float score;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewDeleteParamDto {

        @NotNull
        Long cafeId;
        @NotNull
        Long reviewId;
    }
}
