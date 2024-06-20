package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MateParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MateCreateParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long mateId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MateDeleteParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long mateId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MateListParamDto {

        @NotNull
        Long memberId;
    }
}
