package practiceProject.cmap.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MateResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MateCreateResponseDto {

        Long mateId;
        Long memberId;
        LocalDateTime createdAt;
    }
}
