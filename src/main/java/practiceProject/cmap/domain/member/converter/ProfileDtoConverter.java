package practiceProject.cmap.domain.member.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.member.dto.ProfileParameterDTO;
import practiceProject.cmap.domain.member.dto.ProfileRequestDTO;

@Mapper
public interface ProfileDtoConverter {

    ProfileDtoConverter INSTANCE = Mappers.getMapper(ProfileDtoConverter.class);

    ProfileParameterDTO.ProfileModifyParamDto toProfileModifyParamDto(ProfileRequestDTO.ProfileModifyRequestDto request, Long memberId);
}
