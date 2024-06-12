package practiceProject.cmap.domain.cmap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;

public class CmapRequestDTO {

    @Getter
    public static class CmapCreateRequestDto {

        @NotNull
        CmapStatus status;
        @NotNull
        Long memberId;
    }
}
