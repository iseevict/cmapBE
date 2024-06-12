package practiceProject.cmap.domain.cmap.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CmapResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapCreateResponseDto {

        @NotNull
        Long cmapId;
        @NotEmpty
        LocalDateTime createdAt;
    }
}
