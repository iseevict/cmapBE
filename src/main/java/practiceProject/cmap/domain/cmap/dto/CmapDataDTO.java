package practiceProject.cmap.domain.cmap.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;

import java.math.BigDecimal;
import java.util.List;

public class CmapDataDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapLocationDataDto {

        BigDecimal posX;
        BigDecimal posY;
        String cafeName;
        CmapStatus status;
        Long cafeId;
    }

    @Getter
    public static class CmapJoinCafeDataDto {

        BigDecimal posX;
        BigDecimal posY;
        String cafeName;
        CmapStatus status;
        Long cafeId;

        @QueryProjection
        public CmapJoinCafeDataDto(BigDecimal posX, BigDecimal posY, String cafeName, CmapStatus status, Long cafeId) {

            this.posX = posX;
            this.posY = posY;
            this.cafeName = cafeName;
            this.status = status;
            this.cafeId = cafeId;
        }

    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapListByStatusAndThemaDataDto {

        String cafeName;
        Long cafeId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapDefaultListByStatusDataDto {

        String cafeName;
        Long cafeId;
    }

}
