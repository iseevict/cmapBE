package practiceProject.cmap.domain.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class BoardParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardWriteParamDto {

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

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardModifyParamDto {

        @NotNull
        Long boardId;
        @NotNull
        Long memberId;
        @NotEmpty
        String title;
        @NotEmpty
        String body;

        List<Long> hashtagList;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardDeleteParamDto {

        @NotNull
        Long boardId;
        @NotNull
        Long memberId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardHeartOnParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long boardId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardHeartOffParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long boardId;
    }
}
