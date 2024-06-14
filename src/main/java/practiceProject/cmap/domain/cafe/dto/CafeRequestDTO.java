package practiceProject.cmap.domain.cafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

public class CafeRequestDTO {

    @Getter
    public static class CafeCreateRequestDto {

        @NotEmpty
        String name;
        @NotNull
        BigDecimal posX;
        @NotNull
        BigDecimal posY;
        @Length(max = 50)
        String introduce;
        @NotNull
        Long memberId;
        List<Long> themaList;
    }

    @Getter
    public static class CafeModifyRequestDto {

        @NotNull
        Long memberId;
        @NotEmpty
        String name;
        @NotNull
        BigDecimal posX;
        @NotNull
        BigDecimal posY;
        @Length(max = 50)
        String introduce;
        List<Long> themaList;
    }

    @Getter
    public static class CafeDeleteRequestDto {

        @NotNull
        Long memberId;
    }
}
