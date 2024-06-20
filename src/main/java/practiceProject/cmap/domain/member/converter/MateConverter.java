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
}
