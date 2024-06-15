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
}
