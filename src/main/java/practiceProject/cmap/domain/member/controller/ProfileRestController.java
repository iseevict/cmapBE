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
import practiceProject.cmap.domain.member.converter.ProfileConverter;
import practiceProject.cmap.domain.member.converter.ProfileDtoConverter;
import practiceProject.cmap.domain.member.dto.ProfileParameterDTO;
import practiceProject.cmap.domain.member.dto.ProfileRequestDTO;
import practiceProject.cmap.domain.member.dto.ProfileResponseDTO;
import practiceProject.cmap.domain.member.entity.Profile;
import practiceProject.cmap.domain.member.service.ProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class ProfileRestController {

    private final ProfileService profileService;

    @PatchMapping("/profiles/{memberId}")
    @Operation(summary = "프로필 수정 API", description = "프로필 수정 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PROFILE1001", description = "프로필을 찾지 못했습니다.")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 식별자, PathVariable")
    })
    public ApiResponse<ProfileResponseDTO.ProfileModifyResponseDto> ProfileModify(@RequestBody @Valid ProfileRequestDTO.ProfileModifyRequestDto request, @PathVariable("memberId") Long memberId) {

        ProfileParameterDTO.ProfileModifyParamDto profileModifyParamDto = ProfileDtoConverter.INSTANCE.toProfileModifyParamDto(request, memberId);
        Profile profile = profileService.ProfileModify(profileModifyParamDto);
        return ApiResponse.onSuccess(ProfileConverter.toProfileModifyResultDto(profile));
    }
}
