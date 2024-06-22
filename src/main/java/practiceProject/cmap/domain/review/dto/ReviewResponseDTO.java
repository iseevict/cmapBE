package practiceProject.cmap.domain.review.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewDeleteResponseDto {

        @NotEmpty
        String message;
        @NotEmpty
        LocalDateTime deletedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SingleReviewResponseDto {

        String title;
        String body;
        Float score;
        String writer;
        Long memberId;
        LocalDateTime updatedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberReviewListResponseDto {

        List<ReviewDataDTO.ReviewDataDto> reviewDataDtoList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
