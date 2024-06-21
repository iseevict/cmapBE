package practiceProject.cmap.domain.cmap.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;

import java.time.LocalDateTime;
import java.util.List;

public class CmapResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapCreateResponseDto {

        @NotNull
        Long cmapId;
        @NotEmpty
        LocalDateTime createdAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapStatusChangeResponseDto {

        @NotNull
        Long cmapId;
        @NotEmpty
        CmapStatus status;
        @NotEmpty
        LocalDateTime updatedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapDeleteResponseDto {

        @NotEmpty
        String message;
        @NotEmpty
        LocalDateTime deletedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapLocationResponseDto {

        List<CmapDataDTO.CmapLocationDataDto> cmapLocationDataDtoList;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapWantListResponseDto {

        List<CmapDataDTO.CmapWantListDataDto> cmapWantListDataDtoList;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapDefaultListByStatusResponseDto {

        List<CmapDataDTO.CmapDefaultListByStatusDataDto> cmapDefaultWantListDataDtoList;
    }
}
