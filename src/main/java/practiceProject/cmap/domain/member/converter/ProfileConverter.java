package practiceProject.cmap.domain.member.converter;

import practiceProject.cmap.domain.member.dto.ProfileResponseDTO;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.Profile;

import java.time.LocalDateTime;

public class ProfileConverter {

    public static ProfileResponseDTO.ProfileGetResponseDto toProfileGetResultDto(Profile profile) {

        return ProfileResponseDTO.ProfileGetResponseDto.builder()
                .memberName(profile.getMember().getName())
                .boardNum(profile.getMember().getBoardList().size())
                .reviewNum(profile.getMember().getReviewList().size())
                .introduce(profile.getIntroduce())
                .favoriteCafeTitle(profile.getFavoriteCafeTitle())
                .favoriteCafeBody(profile.getFavoriteCafeBody())
                .build();
    }

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
