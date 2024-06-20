package practiceProject.cmap.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MateDataDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MateListDataDto {

        Long mateId;
        String mateName;
    }
}
