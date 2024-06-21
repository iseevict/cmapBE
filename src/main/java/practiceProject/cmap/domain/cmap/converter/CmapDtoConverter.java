package practiceProject.cmap.domain.cmap.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.dto.CmapRequestDTO;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CmapDtoConverter {

    CmapDtoConverter INSTANCE = Mappers.getMapper(CmapDtoConverter.class);

    CmapParameterDTO.CmapCreateParamDto toCmapCreateParamDto(CmapRequestDTO.CmapCreateRequestDto request, Long cafeId);
    CmapParameterDTO.CmapStatusChangeParamDto toCmapStatusChangeParamDto(CmapRequestDTO.CmapStatusChangeRequestDto request, Long cafeId);
    CmapParameterDTO.CmapDeleteParamDto toCmapDeleteParamDto(CmapRequestDTO.CmapDeleteRequestDto request, Long cafeId);
    CmapParameterDTO.CmapLocationParamDto toCmapLocationParamDto(Long memberId, BigDecimal centerX, BigDecimal centerY, BigDecimal radius);
    CmapParameterDTO.CmapListByStatusAndThemaParamDto toCmapListByStatusAndThemaParamDto(Long memberId, List<Long> themaList, CmapStatus status);
    CmapParameterDTO.CmapDefaultListByStatusParamDto toCmapDefaultListByStatusParamDto(Long memberId, CmapStatus status);
}
