package practiceProject.cmap.domain.member.converter;

import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.dto.MemberRequestDTO;
import practiceProject.cmap.domain.member.dto.MemberResponseDTO;
import practiceProject.cmap.domain.member.entity.Member;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDTO.MemberSignupResponseDto toMemberSignupResultDto(Member member) {
        return MemberResponseDTO.MemberSignupResponseDto.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toNewMember (MemberParameterDTO.MemberSignupParamDto param) {

        return Member.builder()
                .email(param.getEmail())
                .name(param.getName())
                .password(param.getPassword())
                .build();
    }

}
