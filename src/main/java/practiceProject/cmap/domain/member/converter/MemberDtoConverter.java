package practiceProject.cmap.domain.member.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.dto.MemberRequestDTO;

@Mapper
public interface MemberDtoConverter {

    MemberDtoConverter INSTANCE = Mappers.getMapper(MemberDtoConverter.class);

    MemberParameterDTO.MemberSignupParamDto toMemberSignupParamDto(MemberRequestDTO.MemberSignupRequestDto request);
    MemberParameterDTO.MemberSigninParamDto toMemberSigninParamDto(MemberRequestDTO.MemberSigninRequestDto request);
    MemberParameterDTO.MemberChangeStatusParamDto toMemberChangeStatusParamDto(Long memberId);
}
