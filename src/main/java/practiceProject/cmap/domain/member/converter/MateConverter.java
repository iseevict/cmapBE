package practiceProject.cmap.domain.member.converter;

import practiceProject.cmap.domain.member.dto.MateResponseDTO;
import practiceProject.cmap.domain.member.entity.Mate;

import java.time.LocalDateTime;

public class MateConverter {

    public static MateResponseDTO.MateCreateResponseDto toMateCreateResultDto(Mate mate) {

        return MateResponseDTO.MateCreateResponseDto.builder()
                .mateId(mate.getMate().getId())
                .memberId(mate.getMember().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MateResponseDTO.MateDeleteResponseDto toMateDeleteResultDto() {

        return MateResponseDTO.MateDeleteResponseDto.builder()
                .message("삭제 성공입니다.")
                .deletedAt(LocalDateTime.now())
                .build();
    }
}
