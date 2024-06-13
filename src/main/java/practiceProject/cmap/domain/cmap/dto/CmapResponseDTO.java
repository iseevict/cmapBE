package practiceProject.cmap.domain.cmap.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;

import java.time.LocalDateTime;

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
}
