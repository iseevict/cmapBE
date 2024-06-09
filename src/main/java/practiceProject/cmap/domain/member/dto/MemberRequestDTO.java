package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class MemberSignupRequestDto {

        @NotNull @NotBlank
        String email;
        @NotNull @NotBlank
        String password;
        @NotNull @NotBlank
        String name;
    }

    @Getter
    public static class MemberSigninRequestDto {

        @NotNull @NotBlank
        String email;
        @NotNull @NotBlank
        String password;
    }

}
