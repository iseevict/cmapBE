package practiceProject.cmap.domain.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.entity.QBoard;

import java.util.List;

public class BoardDataDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BoardDataDto {

        String title;
        String body;
        List<Long> tagList;
        Long memberId;
        String memberName;
        Long cafeId;
        String cafeName;

    }

}
