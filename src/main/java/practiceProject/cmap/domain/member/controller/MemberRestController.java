package practiceProject.cmap.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.member.converter.DtoConverter;
import practiceProject.cmap.domain.member.converter.MemberConverter;
import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.dto.MemberRequestDTO;
import practiceProject.cmap.domain.member.dto.MemberResponseDTO;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/members/signup")
    @Operation(summary = "회원가입 API", description = "새로운 멤버 회원가입 API, 새 멤버 데이터를 DB에 저장하며 Profile 데이터도 DB에 저장")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1001", description = "이메일 중복입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1002", description = "닉네임 중복입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ApiResponse<MemberResponseDTO.MemberSignupResponseDto> MemberSignup(@RequestBody @Valid MemberRequestDTO.MemberSignupRequestDto request) {
        MemberParameterDTO.MemberSignupParamDto memberSignupParamDto = DtoConverter.INSTANCE.toMemberSignupParamDto(request);
        Member newMember = memberService.MemberSignup(memberSignupParamDto);
        return ApiResponse.onSuccess(MemberConverter.toMemberSignupResultDto(newMember));
    }
}
