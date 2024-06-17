package practiceProject.cmap.domain.member.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ProfileResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ProfileModifyResponseDto {

        @NotNull
        Long profileId;
        @NotEmpty
        LocalDateTime updatedAt;
    }
}
