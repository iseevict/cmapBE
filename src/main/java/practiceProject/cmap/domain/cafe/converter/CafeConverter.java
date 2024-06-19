package practiceProject.cmap.domain.cafe.converter;

import practiceProject.cmap.domain.cafe.dto.CafeDataDTO;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.dto.CafeResponseDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.member.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CafeConverter {

    public static CafeResponseDTO.CafeCreateResponseDto toCafeCreateResultDto (Cafe cafe) {

        return CafeResponseDTO.CafeCreateResponseDto.builder()
                .cafeId(cafe.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CafeResponseDTO.CafeDeleteResponseDto toCafeDeleteResultDto() {

        return CafeResponseDTO.CafeDeleteResponseDto.builder()
                .message("삭제 성공입니다.")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static CafeResponseDTO.CafeModifyResponseDto toCafeModifyResultDto(Cafe cafe) {

        return CafeResponseDTO.CafeModifyResponseDto.builder()
                .cafeId(cafe.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CafeResponseDTO.CafeLocationResponseDto toCafeLocationResultDto(List<Cafe> cafeList) {

        List<CafeDataDTO.CafeLocationDataDto> cafeLocationDataDtoList = cafeList.stream()
                .map(cafe -> {
                    return CafeDataDTO.CafeLocationDataDto.builder()
                            .posX(cafe.getPosX())
                            .posY(cafe.getPosY())
                            .cafeId(cafe.getId())
                            .cafeName(cafe.getName())
                            .build();
                })
                .collect(Collectors.toList());

        return CafeResponseDTO.CafeLocationResponseDto.builder()
                .cafeLocationDataDtoList(cafeLocationDataDtoList)
                .totalCafeNum(cafeLocationDataDtoList.size())
                .build();
    }

    public static CafeResponseDTO.CafeSearchResponseDto toCafeSearchResultDto(List<Cafe> cafeList) {

        List<CafeDataDTO.CafeSearchDataDto> cafeSearchDataDtoList = cafeList.stream()
                .map(cafe -> {
                    return CafeDataDTO.CafeSearchDataDto.builder()
                            .cafeName(cafe.getName())
                            .introduce(cafe.getIntroduce())
                            .reviewNum(cafe.getReviewList().size())
                            .score(cafe.getScore())
                            .build();
                }).collect(Collectors.toList());

        return CafeResponseDTO.CafeSearchResponseDto.builder()
                .cafeSearchDataDtoList(cafeSearchDataDtoList)
                .totalCafeNum(cafeSearchDataDtoList.size())
                .build();
    }

    public static Cafe toCafe (CafeParameterDTO.CafeCreateParamDto param, Member member) {

        return Cafe.builder()
                .name(param.getName())
                .posX(param.getPosX())
                .posY(param.getPosY())
                .introduce(param.getIntroduce())
                .member(member)
                .cafeThemaList(new ArrayList<>())
                .build();
    }
}
