package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProfileParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ProfileModifyParamDto {

        @NotNull
        Long memberId;
        @NotBlank
        String introduce;
        @NotBlank
        String favoriteCafeTitle;
        @NotBlank
        String favoriteCafeBody;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ProfileGetParamDto {

        @NotNull
        Long memberId;
    }
}
