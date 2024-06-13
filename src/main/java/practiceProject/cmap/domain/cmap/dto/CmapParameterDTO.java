package practiceProject.cmap.domain.cmap.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;

public class CmapParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapCreateParamDto {

        @NotNull
        Long cafeId;
        @NotNull
        CmapStatus status;
        @NotNull
        Long memberId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapStatusChangeParamDto {

        @NotNull
        Long cafeId;
        @NotNull
        Long memberId;
        @NotNull
        CmapStatus status;
    }
}
