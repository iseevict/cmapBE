package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class MemberParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberSignupParamDto {

        @NotEmpty
        String email;
        @NotEmpty
        String password;
        @NotEmpty
        String name;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberSigninParamDto {

        @NotEmpty
        String email;
        @NotEmpty
        String password;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberChangeStatusParamDto {

        @NotNull
        Long memberId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberChangeRoleParamDto {

        @NotNull
        Long memberId;
    }

}
