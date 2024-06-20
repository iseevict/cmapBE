package practiceProject.cmap.domain.cmap.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.dto.CmapRequestDTO;

import java.math.BigDecimal;

@Mapper
public interface CmapDtoConverter {

    CmapDtoConverter INSTANCE = Mappers.getMapper(CmapDtoConverter.class);

    CmapParameterDTO.CmapCreateParamDto toCmapCreateParamDto(CmapRequestDTO.CmapCreateRequestDto request, Long cafeId);
    CmapParameterDTO.CmapStatusChangeParamDto toCmapStatusChangeParamDto(CmapRequestDTO.CmapStatusChangeRequestDto request, Long cafeId);
    CmapParameterDTO.CmapDeleteParamDto toCmapDeleteParamDto(CmapRequestDTO.CmapDeleteRequestDto request, Long cafeId);
    CmapParameterDTO.CmapLocationParamDto toCmapLocationParamDto(Long memberId, BigDecimal centerX, BigDecimal centerY, BigDecimal radius);
}
