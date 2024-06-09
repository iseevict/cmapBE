package practiceProject.cmap.domain.member.converter;

import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.dto.MemberRequestDTO;
import practiceProject.cmap.domain.member.dto.MemberResponseDTO;
import practiceProject.cmap.domain.member.entity.Member;

import java.time.LocalDateTime;
import java.util.Map;

public class MemberConverter {

    public static MemberResponseDTO.MemberSignupResponseDto toMemberSignupResultDto(Member member) {
        return MemberResponseDTO.MemberSignupResponseDto.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberResponseDTO.MemberSigninResponseDto toMemberSigninResultDto(Map<Long, String> loginData) {
        return loginData.entrySet().stream()
                .findFirst()
                .map(entry -> MemberResponseDTO.MemberSigninResponseDto.builder()
                        .memberId(entry.getKey())
                        .Token(entry.getValue())
                        .loginAt(LocalDateTime.now())
                        .build())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._INTERNAL_SERVER_ERROR));
    }

    public static MemberResponseDTO.MemberChangeStatusResponseDto toMemberChangeStatusDto(Member member) {
        return MemberResponseDTO.MemberChangeStatusResponseDto.builder()
                .memberId(member.getId())
                .inactiveAt(member.getInactiveAt())
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
