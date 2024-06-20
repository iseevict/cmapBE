package practiceProject.cmap.domain.member.controller;

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
import practiceProject.cmap.domain.member.converter.MateConverter;
import practiceProject.cmap.domain.member.converter.MateDtoConverter;
import practiceProject.cmap.domain.member.dto.MateParameterDTO;
import practiceProject.cmap.domain.member.dto.MateRequestDTO;
import practiceProject.cmap.domain.member.dto.MateResponseDTO;
import practiceProject.cmap.domain.member.entity.Mate;
import practiceProject.cmap.domain.member.service.MateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class MateRestController {

    private final MateService mateService;

    @PostMapping("/profiles/{mateId}")
    @Operation(summary = "Mate 설정 API", description = "Mate 설정 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MATE1001", description = "이미 Mate 상태입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MATE1003", description = "본인은 Mate로 설정할 수 없습니다.")
    })
    @Parameters({
            @Parameter(name = "mateId", description = "회원(mate) 식별자, PathVariable")
    })
    public ApiResponse<MateResponseDTO.MateCreateResponseDto> MateCreate(@RequestBody @Valid MateRequestDTO.MateCreateRequestDto request, @PathVariable("mateId") Long mateId) {

        MateParameterDTO.MateCreateParamDto mateCreateParamDto = MateDtoConverter.INSTANCE.toMateCreateParamDto(request, mateId);
        Mate mate = mateService.MateCreate(mateCreateParamDto);
        return ApiResponse.onSuccess(MateConverter.toMateCreateResultDto(mate));
    }

    @DeleteMapping("/profiles/{mateId}")
    @Operation(summary = "Mate 삭제 API", description = "Mate 삭제 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MATE1002", description = "Mate를 찾을 수 없습니다.")
    })
    @Parameters({
            @Parameter(name = "mateId", description = "회원(mate) 식별자, PathVariable")
    })
    public ApiResponse<MateResponseDTO.MateDeleteResponseDto> MateDelete(@RequestBody @Valid MateRequestDTO.MateDeleteRequestDto request, @PathVariable("mateId") Long mateId) {

        MateParameterDTO.MateDeleteParamDto mateDeleteParamDto = MateDtoConverter.INSTANCE.toMateDeleteParamDto(request, mateId);
        mateService.MateDelete(mateDeleteParamDto);
        return ApiResponse.onSuccess(MateConverter.toMateDeleteResultDto());
    }

    @GetMapping("/mates/{memberId}")
    @Operation(summary = "Mate 리스트 가져오기 API", description = "Mate 리스트 가져오기 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 식별자, PathVariable")
    })
    public ApiResponse<MateResponseDTO.MateListResponseDto> MateList(@PathVariable("memberId") Long memberId) {

        MateParameterDTO.MateListParamDto mateListParamDto = MateDtoConverter.INSTANCE.toMateListParamDto(memberId);
        List<Mate> mateList = mateService.MateList(mateListParamDto);
        return ApiResponse.onSuccess(MateConverter.toMateListResultDto(mateList, memberId));
    }
}
