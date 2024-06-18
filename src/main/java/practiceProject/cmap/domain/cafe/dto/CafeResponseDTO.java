package practiceProject.cmap.domain.cafe.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CafeResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeCreateResponseDto {

        @NotNull
        Long cafeId;
        @NotEmpty
        LocalDateTime createdAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeModifyResponseDto {

        @NotNull
        Long cafeId;
        @NotEmpty
        LocalDateTime updatedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeDeleteResponseDto {

        @NotEmpty
        String message;
        @NotEmpty
        LocalDateTime deletedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeLocationResponseDto {
        List<CafeLocationDataDto> cafeLocationDataDtoList;
        Integer totalCafeNum;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeLocationDataDto {

        BigDecimal posX;
        BigDecimal posY;
        Long cafeId;
        String cafeName;
    }
}
