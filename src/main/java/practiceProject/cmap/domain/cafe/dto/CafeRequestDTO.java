package practiceProject.cmap.domain.cafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class CafeRequestDTO {

    @Getter
    public static class CafeCreateRequestDto {

        @NotNull @NotBlank
        String name;
        @NotNull
        BigDecimal posX;
        @NotNull
        BigDecimal posY;
        @Length(max = 50)
        String introduce;
    }
}
