package practiceProject.cmap.domain.cafe.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CafeResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeCreateResponseDto {

        @NotNull
        Long cafeId;
        @NotEmpty
        LocalDateTime createdAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeDeleteResponseDto {

        @NotEmpty
        String message;
        @NotEmpty
        LocalDateTime deletedAt;
    }
}
