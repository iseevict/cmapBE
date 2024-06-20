package practiceProject.cmap.domain.cmap.converter;

import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cmap.dto.CmapDataDTO;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.dto.CmapResponseDTO;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.member.entity.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CmapConverter {

    public static CmapResponseDTO.CmapLocationResponseDto toCmapLocationResultDto(List<CmapDataDTO.CmapJoinCafeDataDto> cmapJoinCafeDataDtoList) {

        List<CmapDataDTO.CmapLocationDataDto> cmapLocationDataDtoList = cmapJoinCafeDataDtoList.stream()
                .map(cmapJoinCafeDataDto ->
                        CmapDataDTO.CmapLocationDataDto.builder()
                                .posX(cmapJoinCafeDataDto.getPosX())
                                .posY(cmapJoinCafeDataDto.getPosY())
                                .cafeName(cmapJoinCafeDataDto.getCafeName())
                                .cafeId(cmapJoinCafeDataDto.getCafeId())
                                .status(cmapJoinCafeDataDto.getStatus())
                                .build()
                ).collect(Collectors.toList());

        return CmapResponseDTO.CmapLocationResponseDto.builder()
                .cmapLocationDataDtoList(cmapLocationDataDtoList)
                .build();
    }

    public static CmapResponseDTO.CmapCreateResponseDto toCmapCreateResultDto(Cmap cmap) {

        return CmapResponseDTO.CmapCreateResponseDto.builder()
                .cmapId(cmap.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CmapResponseDTO.CmapStatusChangeResponseDto toCmapStatusChangeResultDto(Cmap cmap) {

        return CmapResponseDTO.CmapStatusChangeResponseDto.builder()
                .cmapId(cmap.getId())
                .status(cmap.getStatus())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CmapResponseDTO.CmapDeleteResponseDto toCmapDeleteResultDto() {

        return CmapResponseDTO.CmapDeleteResponseDto.builder()
                .message("삭제 성공했습니다.")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static Cmap toCmap(CmapParameterDTO.CmapCreateParamDto param) {

        return Cmap.builder()
                .status(param.getStatus())
                .build();
    }
}
