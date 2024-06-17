package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ProfileRequestDTO {

    @Getter
    public static class ProfileModifyRequestDto {

        @NotBlank
        String introduce;
        @NotBlank
        String favoriteCafeTitle;
        @NotBlank
        String favoriteCafeBody;
    }
}
