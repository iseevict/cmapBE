package practiceProject.cmap.domain.cafe.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.dto.CafeRequestDTO;

@Mapper
public interface CafeDtoConverter {

    CafeDtoConverter INSTANCE = Mappers.getMapper(CafeDtoConverter.class);

    CafeParameterDTO.CafeCreateParamDto toCafeCreateParamDto(CafeRequestDTO.CafeCreateRequestDto request);
    CafeParameterDTO.CafeDeleteParamDto toCafeDeleteParamDto(CafeRequestDTO.CafeDeleteRequestDto request, Long cafeId);
}
