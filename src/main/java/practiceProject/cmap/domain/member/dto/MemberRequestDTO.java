package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class MemberSignupRequestDto {

        @NotEmpty
        String email;
        @NotEmpty
        String password;
        @NotEmpty
        String name;
    }

    @Getter
    public static class MemberSigninRequestDto {

        @NotEmpty
        String email;
        @NotEmpty
        String password;
    }

}
