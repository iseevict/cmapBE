package practiceProject.cmap.domain.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;
import practiceProject.cmap.domain.review.dto.ReviewDataDTO;
import practiceProject.cmap.domain.review.entity.Review;
import practiceProject.cmap.domain.thema.entity.Thema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CafeDataDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeLocationDataDto {

        BigDecimal posX;
        BigDecimal posY;
        Long cafeId;
        String cafeName;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeSearchDataDto {

        String cafeName;
        String introduce;
        Integer reviewNum;
        Float score;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeDetailDataDto {

        String cafeName;
        String introduce;
        Float score;
        Integer boardNum;
        Integer reviewNum;
        List<Long> themaList;
        CmapStatus status;
        ReviewDataDTO.ReviewPreviewListDataDto reviewPreviewListDataDto;
    }

}
