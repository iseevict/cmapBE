package practiceProject.cmap.domain.member.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.member.dto.MateParameterDTO;
import practiceProject.cmap.domain.member.dto.MateRequestDTO;

@Mapper
public interface MateDtoConverter {

    MateDtoConverter INSTANCE = Mappers.getMapper(MateDtoConverter.class);

    MateParameterDTO.MateCreateParamDto toMateCreateParamDto(MateRequestDTO.MateCreateRequestDto request, Long mateId);
}
