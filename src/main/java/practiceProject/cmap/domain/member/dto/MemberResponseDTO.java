package practiceProject.cmap.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberSignupResponseDto {

        Long memberId;
        LocalDateTime createdAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberSigninResponseDto {

        Long memberId;
        String Token;
        LocalDateTime loginAt;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MemberChangeStatusResponseDto {

        Long memberId;
        LocalDateTime inactiveAt;
    }
}
