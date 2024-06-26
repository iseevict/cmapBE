package practiceProject.cmap.domain.cafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

public class CafeParameterDTO {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeCreateParamDto {

        @NotEmpty
        String name;
        @NotNull
        BigDecimal posX;
        @NotNull
        BigDecimal posY;
        @Length(max = 50)
        String introduce;
        @NotEmpty
        Long memberId;
        List<Long> themaList;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeModifyParamDto {

        @NotNull
        Long cafeId;
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

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeDeleteParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long cafeId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeLocationParamDto {

        @NotNull
        BigDecimal centerX;
        @NotNull
        BigDecimal centerY;
        @NotNull
        BigDecimal radius;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeSearchParamDto {

        @NotNull
        BigDecimal centerX;
        @NotNull
        BigDecimal centerY;
        @NotNull
        BigDecimal radius;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CafeDetailParamDto {

        @NotNull
        Long memberId;
        @NotNull
        Long cafeId;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class RandomCafeParamDto {

        @NotNull
        Long memberId;
        List<Long> themaList;
    }
}
