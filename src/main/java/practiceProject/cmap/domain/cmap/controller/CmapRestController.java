package practiceProject.cmap.domain.cmap.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.cmap.converter.CmapConverter;
import practiceProject.cmap.domain.cmap.converter.CmapDtoConverter;
import practiceProject.cmap.domain.cmap.dto.CmapDataDTO;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.dto.CmapRequestDTO;
import practiceProject.cmap.domain.cmap.dto.CmapResponseDTO;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.cmap.service.CmapService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/camp")
public class CmapRestController {

    private final CmapService cmapService;

    @PostMapping("/cmaps/{cafeId}")
    @Operation(summary = "cmap 추가 API", description = "cmap 추가 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1004", description = "멤버를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1002", description = "카페를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CMAP1001", description = "이미 Cmap에 저장된 카페입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "cafeId", description = "카페 식별자, PathVariable")
    })
    public ApiResponse<CmapResponseDTO.CmapCreateResponseDto> CmapCreate(@RequestBody @Valid CmapRequestDTO.CmapCreateRequestDto request, @PathVariable("cafeId") Long cafeId) {
        CmapParameterDTO.CmapCreateParamDto cmapCreateParamDto = CmapDtoConverter.INSTANCE.toCmapCreateParamDto(request, cafeId);
        Cmap cmap = cmapService.CmapCreate(cmapCreateParamDto);
        return ApiResponse.onSuccess(CmapConverter.toCmapCreateResultDto(cmap));
    }

    @PatchMapping("/cmaps/{cafeId}")
    @Operation(summary = "Cmap 상태 수정 API", description = "Cmap 상태 수정 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1004", description = "멤버를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1002", description = "카페를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CMAP1002", description = "Cmap을 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "cafeId", description = "카페 식별자, PathVariable")
    })
    public ApiResponse<CmapResponseDTO.CmapStatusChangeResponseDto> CmapStatusChange(@RequestBody @Valid CmapRequestDTO.CmapStatusChangeRequestDto request, @PathVariable("cafeId") Long cafeId) {
        CmapParameterDTO.CmapStatusChangeParamDto cmapStatusChangeParamDto = CmapDtoConverter.INSTANCE.toCmapStatusChangeParamDto(request, cafeId);
        Cmap cmap = cmapService.CmapStatusChange(cmapStatusChangeParamDto);
        return ApiResponse.onSuccess(CmapConverter.toCmapStatusChangeResultDto(cmap));
    }

    @DeleteMapping("/cmaps/{cafeId}")
    @Operation(summary = "Cmap 삭제 API", description = "Cmap 삭제 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1004", description = "멤버를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1002", description = "카페를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CMAP1002", description = "Cmap을 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "cafeId", description = "카페 식별자, PathVariable")
    })
    public ApiResponse<CmapResponseDTO.CmapDeleteResponseDto> CmapDelete(@RequestBody @Valid CmapRequestDTO.CmapDeleteRequestDto request, @PathVariable("cafeId") Long cafeId) {
        CmapParameterDTO.CmapDeleteParamDto cmapDeleteParamDto = CmapDtoConverter.INSTANCE.toCmapDeleteParamDto(request, cafeId);
        cmapService.CmapDelete(cmapDeleteParamDto);
        return ApiResponse.onSuccess(CmapConverter.toCmapDeleteResultDto());
    }

    @GetMapping("/cmaps/{memberId}")
    @Operation(summary = "Cmap 지도 화면 API", description = "Cmap 지도 화면 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<CmapResponseDTO.CmapLocationResponseDto> CmapLocation(@PathVariable("memberId") Long memberId,
                                                                             @RequestParam BigDecimal centerX,
                                                                             @RequestParam BigDecimal centerY,
                                                                             @RequestParam BigDecimal radius) {

        CmapParameterDTO.CmapLocationParamDto cmapLocationParamDto = CmapDtoConverter.INSTANCE.toCmapLocationParamDto(memberId, centerX, centerY, radius);
        List<CmapDataDTO.CmapJoinCafeDataDto> cmapJoinCafeDataDtoList = cmapService.CmapLocation(cmapLocationParamDto);
        return ApiResponse.onSuccess(CmapConverter.toCmapLocationResultDto(cmapJoinCafeDataDtoList));
    }

    @GetMapping("/want-list/{memberId}/default")
    @Operation(summary = "유저 WANT List 가져오기 API", description = "유저 WANT List 가져오기 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<CmapResponseDTO.CmapDefaultWantListResponseDto> CmapDefaultWantList(@PathVariable("memberId") Long memberId) {

        CmapParameterDTO.CmapDefaultWantListParamDto cmapDefaultWantListParamDto = CmapDtoConverter.INSTANCE.toCmapDefaultWantListParamDto(memberId);
        List<Cmap> cmapList = cmapService.CmapDefaultWantList(cmapDefaultWantListParamDto);
        return ApiResponse.onSuccess(CmapConverter.toCmapDefaultWantListResultDto(cmapList));
    }
}
