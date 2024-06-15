package practiceProject.cmap.domain.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class BoardRequestDTO {

    @Getter
    public static class BoardWriteRequestDto {

        @NotNull
        Long memberId;
        @NotNull
        Long cafeId;
        @NotEmpty
        String title;
        @NotEmpty
        String body;

        List<Long> hashtagList;
    }

    @Getter
    public static class BoardModifyRequestDto {

        @NotNull
        Long memberId;
        @NotEmpty
        String title;
        @NotEmpty
        String body;

        List<Long> hashtagList;
    }

    @Getter
    public static class BoardDeleteRequestDto {

        @NotNull
        Long memberId;
    }

    @Getter
    public static class BoardHeartOnRequestDto {

        @NotNull
        Long memberId;
    }

}
