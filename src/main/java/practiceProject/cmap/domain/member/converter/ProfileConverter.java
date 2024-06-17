package practiceProject.cmap.domain.member.converter;

import practiceProject.cmap.domain.member.dto.ProfileResponseDTO;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.Profile;

import java.time.LocalDateTime;

public class ProfileConverter {

    public static ProfileResponseDTO.ProfileModifyResponseDto toProfileModifyResultDto(Profile profile) {

        return ProfileResponseDTO.ProfileModifyResponseDto.builder()
                .profileId(profile.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static Profile toProfile (Member member) {
        return Profile.builder()
                .member(member)
                .build();
    }
}
