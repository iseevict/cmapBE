package practiceProject.cmap.domain.cmap.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;

import java.math.BigDecimal;
import java.util.List;

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

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapDeleteParamDto {

        @NotNull
        Long cafeId;
        @NotNull
        Long memberId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapLocationParamDto {

        @NotNull
        Long memberId;
        @NotNull
        BigDecimal centerX;
        @NotNull
        BigDecimal centerY;
        @NotNull
        BigDecimal radius;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapListByStatusAndThemaParamDto {

        @NotNull
        Long memberId;
        @NotNull
        CmapStatus status;
        List<Long> themaList;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CmapDefaultListByStatusParamDto {

        @NotNull
        Long memberId;
        @NotNull
        CmapStatus status;
    }

}
