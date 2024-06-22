package practiceProject.cmap.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentDataDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CommentDataDto {

        String body;
        String writer;
        Long memberId;
        LocalDateTime updatedAt;
    }
}
