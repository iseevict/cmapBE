package practiceProject.cmap.domain.member.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.dto.MemberRequestDTO;

import java.time.LocalDateTime;

@Mapper
public interface DtoConverter {

    DtoConverter INSTANCE = Mappers.getMapper(DtoConverter.class);

    MemberParameterDTO.MemberSignupParamDto toMemberSignupParamDto(MemberRequestDTO.MemberSignupRequestDto request);
}
