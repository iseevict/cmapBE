package practiceProject.cmap.domain.cafe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.cafe.converter.CafeConverter;
import practiceProject.cmap.domain.cafe.converter.CafeDtoConverter;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.dto.CafeRequestDTO;
import practiceProject.cmap.domain.cafe.dto.CafeResponseDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.service.CafeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class CafeRestController {

    private final CafeService cafeService;

    @PostMapping("/cafes")
    @Operation(summary = "카페 추가 API", description = "카페 추가 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1001", description = "이미 카페가 존재하는 위치입니다.")
    })
    public ApiResponse<CafeResponseDTO.CafeCreateResponseDto> CafeCreate(@RequestBody @Valid CafeRequestDTO.CafeCreateRequestDto request) {
        CafeParameterDTO.CafeCreateParamDTO cafeCreateParamDTO = CafeDtoConverter.INSTANCE.toCafeCreateParamDto(request);
        Cafe cafe = cafeService.CafeCreate(cafeCreateParamDTO);
        return ApiResponse.onSuccess(CafeConverter.toCafeCreateResultDto(cafe));
    }
}
