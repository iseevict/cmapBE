package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MateRequestDTO {

    @Getter
    public static class MateCreateRequestDto {

        @NotNull
        Long memberId;
    }
}
