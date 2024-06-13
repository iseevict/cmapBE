package practiceProject.cmap.domain.cmap.converter;

import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.dto.CmapResponseDTO;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.member.entity.Member;

import java.time.LocalDateTime;

public class CmapConverter {

    public static CmapResponseDTO.CmapCreateResponseDto toCmapCreateResultDto (Cmap cmap) {

        return CmapResponseDTO.CmapCreateResponseDto.builder()
                .cmapId(cmap.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CmapResponseDTO.CmapStatusChangeResponseDto toCmapStatusChangeResultDto (Cmap cmap) {

        return CmapResponseDTO.CmapStatusChangeResponseDto.builder()
                .cmapId(cmap.getId())
                .status(cmap.getStatus())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static Cmap toCmap(CmapParameterDTO.CmapCreateParamDto param) {

        return Cmap.builder()
                .status(param.getStatus())
                .build();
    }
}
