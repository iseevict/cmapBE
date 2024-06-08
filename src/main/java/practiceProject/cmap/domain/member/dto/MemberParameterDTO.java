package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class MemberParameterDTO {

    @Getter @Builder @AllArgsConstructor @NoArgsConstructor
    public static class MemberSignupParamDto {

        @NotNull @NotBlank
        String email;
        @NotNull @NotBlank
        String password;
        @NotNull @NotBlank
        String name;
    }
}
