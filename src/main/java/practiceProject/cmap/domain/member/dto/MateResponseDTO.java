package practiceProject.cmap.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MateResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MateCreateResponseDto {

        Long mateId;
        Long memberId;
        LocalDateTime createdAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MateDeleteResponseDto {

        String message;
        LocalDateTime deletedAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MateListResponseDto {

        Long memberId;
        List<MateDataDTO.MateListDataDto> mateListDataDtoList;
    }
}
