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
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.dto.CmapRequestDTO;
import practiceProject.cmap.domain.cmap.dto.CmapResponseDTO;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.cmap.service.CmapService;

import java.time.LocalDateTime;

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
    public ApiResponse<CmapResponseDTO.CmapCreateResponseDto> CreateCmap(@RequestBody @Valid CmapRequestDTO.CmapCreateRequestDto request, @PathVariable("cafeId") Long cafeId) {
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
    public ApiResponse<CmapResponseDTO.CmapStatusChangeResponseDto> ChangeCmapStatus(@RequestBody @Valid CmapRequestDTO.CmapStatusChangeRequestDto request, @PathVariable("cafeId") Long cafeId) {
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
    public ApiResponse<CmapResponseDTO.CmapDeleteResponseDto> DeleteCmap(@RequestBody @Valid CmapRequestDTO.CmapDeleteRequestDto request, @PathVariable("cafeId") Long cafeId) {
        CmapParameterDTO.CmapDeleteParamDto cmapDeleteParamDto = CmapDtoConverter.INSTANCE.toCmapDeleteParamDto(request, cafeId);
        cmapService.CmapDelete(cmapDeleteParamDto);
        return ApiResponse.onSuccess(CmapConverter.toCmapDeleteResultDto());
    }
}
