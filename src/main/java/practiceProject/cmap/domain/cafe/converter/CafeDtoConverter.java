package practiceProject.cmap.domain.cafe.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.dto.CafeRequestDTO;

import java.math.BigDecimal;

@Mapper
public interface CafeDtoConverter {

    CafeDtoConverter INSTANCE = Mappers.getMapper(CafeDtoConverter.class);

    CafeParameterDTO.CafeCreateParamDto toCafeCreateParamDto(CafeRequestDTO.CafeCreateRequestDto request);
    CafeParameterDTO.CafeDeleteParamDto toCafeDeleteParamDto(CafeRequestDTO.CafeDeleteRequestDto request, Long cafeId);
    CafeParameterDTO.CafeModifyParamDto toCafeModifyParamDto(CafeRequestDTO.CafeModifyRequestDto request, Long cafeId);
    CafeParameterDTO.CafeLocationParamDto toCafeLocationParamDto(BigDecimal centerX, BigDecimal centerY, BigDecimal radius);
    CafeParameterDTO.CafeSearchParamDto toCafeSearchParamDto(BigDecimal centerX, BigDecimal centerY, BigDecimal radius);
    CafeParameterDTO.CafeDetailParamDto toCafeDetailParamDto(Long memberId, Long cafeId);
}
