package practiceProject.cmap.domain.cafe.converter;

import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.dto.CafeResponseDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.member.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CafeConverter {

    public static CafeResponseDTO.CafeCreateResponseDto toCafeCreateResultDto (Cafe cafe) {

        return CafeResponseDTO.CafeCreateResponseDto.builder()
                .cafeId(cafe.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Cafe toCafe (CafeParameterDTO.CafeCreateParamDTO param, Member member) {

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
