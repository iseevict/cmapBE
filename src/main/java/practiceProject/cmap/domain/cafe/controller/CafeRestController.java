package practiceProject.cmap.domain.cafe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.cafe.converter.CafeConverter;
import practiceProject.cmap.domain.cafe.converter.CafeDtoConverter;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.dto.CafeRequestDTO;
import practiceProject.cmap.domain.cafe.dto.CafeResponseDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.service.CafeService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class CafeRestController {

    private final CafeService cafeService;

    @PostMapping("/cafes")
    @Operation(summary = "카페 추가 API", description = "카페 추가 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1001", description = "이미 카페가 존재하는 위치입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "THEMA1001", description = "없는 테마입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1004", description = "없는 회원입니다.")
    })
    public ApiResponse<CafeResponseDTO.CafeCreateResponseDto> CafeCreate(@RequestBody @Valid CafeRequestDTO.CafeCreateRequestDto request) {
        CafeParameterDTO.CafeCreateParamDto cafeCreateParamDto = CafeDtoConverter.INSTANCE.toCafeCreateParamDto(request);
        Cafe cafe = cafeService.CafeCreate(cafeCreateParamDto);
        return ApiResponse.onSuccess(CafeConverter.toCafeCreateResultDto(cafe));
    }

    @PatchMapping("/cafes/{cafeId}")
    @Operation(summary = "카페 수정 API", description = "카페 수정 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1002", description = "카페를 찾지 못했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1003", description = "카페 주인이 아닙니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1001", description = "이미 카페가 존재하는 위치입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "THEMA1001", description = "없는 테마입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1004", description = "없는 회원입니다.")
    })
    @Parameters({
            @Parameter(name = "cafeId", description = "카페 식별자, PathVariable")
    })
    public ApiResponse<CafeResponseDTO.CafeModifyResponseDto> CafeModify(@RequestBody @Valid CafeRequestDTO.CafeModifyRequestDto request, @PathVariable("cafeId") Long cafeId) {
        CafeParameterDTO.CafeModifyParamDto cafeModifyParamDto = CafeDtoConverter.INSTANCE.toCafeModifyParamDto(request, cafeId);
        Cafe cafe = cafeService.CafeModify(cafeModifyParamDto);
        return ApiResponse.onSuccess(CafeConverter.toCafeModifyResultDto(cafe));
    }

    @DeleteMapping("/cafes/{cafeId}")
    @Operation(summary = "카페 삭제 API", description = "카페 삭제 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1002", description = "카페를 찾지 못했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1003", description = "카페 주인이 아닙니다."),
    })
    @Parameters({
            @Parameter(name = "cafeId", description = "카페 식별자, PathVariable")
    })
    public ApiResponse<CafeResponseDTO.CafeDeleteResponseDto> CafeDelete(@RequestBody @Valid CafeRequestDTO.CafeDeleteRequestDto request, @PathVariable("cafeId") Long cafeId) {
        CafeParameterDTO.CafeDeleteParamDto cafeDeleteParamDto = CafeDtoConverter.INSTANCE.toCafeDeleteParamDto(request, cafeId);
        cafeService.CafeDelete(cafeDeleteParamDto);
        return ApiResponse.onSuccess(CafeConverter.toCafeDeleteResultDto());
    }

    @GetMapping("/cafes")
    @Operation(summary = "지도 화면 API", description = "지도 화면 API 입니다. 범위 내에 있는 카페들의 위경도 데이터를 가져옵니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<CafeResponseDTO.CafeLocationResponseDto> CafeLocationGet(
            @RequestParam BigDecimal centerX,
            @RequestParam BigDecimal centerY,
            @RequestParam BigDecimal radius) {

        CafeParameterDTO.CafeLocationParamDto cafeLocationParamDto = CafeDtoConverter.INSTANCE.toCafeLocationParamDto(centerX, centerY, radius);
        List<Cafe> cafeList = cafeService.CafeLocationGet(cafeLocationParamDto);
        return ApiResponse.onSuccess(CafeConverter.toCafeLocationResultDto(cafeList));
    }
}
