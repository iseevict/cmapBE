package practiceProject.cmap.domain.cafe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.cafe.converter.CafeConverter;
import practiceProject.cmap.domain.cafe.converter.CafeDtoConverter;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.dto.CafeRequestDTO;
import practiceProject.cmap.domain.cafe.dto.CafeResponseDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.entity.mapping.CafeThema;
import practiceProject.cmap.domain.cafe.service.CafeService;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;
import practiceProject.cmap.domain.cmap.service.CmapService;
import practiceProject.cmap.domain.review.entity.Review;
import practiceProject.cmap.domain.review.service.ReviewService;
import practiceProject.cmap.domain.thema.entity.Thema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class CafeRestController {

    private final CafeService cafeService;
    private final ReviewService reviewService;
    private final CmapService cmapService;

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
    public ApiResponse<CafeResponseDTO.CafeLocationResponseDto> CafeLocation(
            @RequestParam BigDecimal centerX,
            @RequestParam BigDecimal centerY,
            @RequestParam BigDecimal radius) {

        CafeParameterDTO.CafeLocationParamDto cafeLocationParamDto = CafeDtoConverter.INSTANCE.toCafeLocationParamDto(centerX, centerY, radius);
        List<Cafe> cafeList = cafeService.CafeLocation(cafeLocationParamDto);
        return ApiResponse.onSuccess(CafeConverter.toCafeLocationResultDto(cafeList));
    }

    @GetMapping("/cafes/search")
    @Operation(summary = "지도 검색창 API", description = "지도 검색창 API 입니다. 범위 내에 있는 카페들의 데이터를 가져옵니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<CafeResponseDTO.CafeSearchResponseDto> CafeSearch(
            @RequestParam BigDecimal centerX,
            @RequestParam BigDecimal centerY,
            @RequestParam BigDecimal radius ) {

        CafeParameterDTO.CafeSearchParamDto cafeSearchParamDto = CafeDtoConverter.INSTANCE.toCafeSearchParamDto(centerX, centerY, radius);
        List<Cafe> cafeList = cafeService.CafeSearch(cafeSearchParamDto);
        return ApiResponse.onSuccess(CafeConverter.toCafeSearchResultDto(cafeList));
    }

    @GetMapping("/cafes/{cafeId}")
    @Operation(summary = "카페 데이터 가져오기 API", description = "카페 데이터 가져오기 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<CafeResponseDTO.CafeDetailResponseDto> CafeDetail(
            @RequestParam Long memberId,
            @PathVariable("cafeId") Long cafeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        CafeParameterDTO.CafeDetailParamDto cafeDetailParamDto = CafeDtoConverter.INSTANCE.toCafeDetailParamDto(memberId, cafeId);
        Cafe cafe = cafeService.CafeDetail(cafeDetailParamDto);
        Slice<Review> reviewSlice = reviewService.CafeDetailReviewList(cafe, PageRequest.of(page, size));
        List<CafeThema> cafeThemaList = cafeService.CafeDetailThema(cafe);
        CmapStatus cmapStatus = cmapService.CmapStatusCheck(cafeDetailParamDto);
        return ApiResponse.onSuccess(CafeConverter.toCafeDetailResultDto(cafe, reviewSlice, cafeThemaList, cmapStatus));
    }

    @GetMapping("/random")
    @Operation(summary = "랜덤 카페id 가져오기 API", description = "랜덤 카페id 가져오기 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1004", description = "조건에 해당하는 카페가 없습니다.")
    })
    public ApiResponse<CafeResponseDTO.RandomCafeResponseDto> RandomCafe(@RequestParam Long memberId,
                                                                         @RequestParam List<Long> themaList) {

        if (themaList == null) themaList = new ArrayList<>();
        CafeParameterDTO.RandomCafeParamDto randomCafeParamDto = CafeDtoConverter.INSTANCE.toRandomCafeParamDto(memberId, themaList);
        Cafe cafe = cafeService.RandomCafe(randomCafeParamDto);
        return ApiResponse.onSuccess(CafeConverter.toRandomCafeResultDto(cafe));
    }
}
