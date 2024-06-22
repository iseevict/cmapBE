package practiceProject.cmap.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewDataDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewPreviewListDataDto {

        List<ReviewPreviewDataDto> reviewPreviewDataDtoList;
        Boolean hasNext;
        Integer pageNum;
        Integer pageSize;
        Integer elementNum;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewPreviewDataDto {

        String body;
        Long memberId;
        String writer;
        LocalDateTime updatedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ReviewDataDto {

        Long reviewId;
        String title;
        String body;
        LocalDateTime updatedAt;
        Long cafeId;
        String cafeName;
    }
}
