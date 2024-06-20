package practiceProject.cmap.domain.member.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.member.dto.MateParameterDTO;
import practiceProject.cmap.domain.member.dto.MateRequestDTO;
import practiceProject.cmap.domain.member.dto.MateResponseDTO;

@Mapper
public interface MateDtoConverter {

    MateDtoConverter INSTANCE = Mappers.getMapper(MateDtoConverter.class);

    MateParameterDTO.MateCreateParamDto toMateCreateParamDto(MateRequestDTO.MateCreateRequestDto request, Long mateId);
    MateParameterDTO.MateDeleteParamDto toMateDeleteParamDto(MateRequestDTO.MateDeleteRequestDto request, Long mateId);
    MateParameterDTO.MateListParamDto toMateListParamDto(Long memberId);
}
