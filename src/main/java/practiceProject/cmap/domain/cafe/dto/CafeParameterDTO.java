package practiceProject.cmap.domain.cafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class CafeParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeCreateParamDTO {

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
