package practiceProject.cmap.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.domain.member.converter.MemberDtoConverter;
import practiceProject.cmap.domain.member.converter.MemberConverter;
import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.dto.MemberRequestDTO;
import practiceProject.cmap.domain.member.dto.MemberResponseDTO;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.service.MemberService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API", description = "새로운 멤버 회원가입 API, 새 멤버 데이터를 DB에 저장하며 Profile 데이터도 DB에 저장")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1001", description = "이메일 중복입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1002", description = "닉네임 중복입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ApiResponse<MemberResponseDTO.MemberSignupResponseDto> MemberSignup(@RequestBody @Valid MemberRequestDTO.MemberSignupRequestDto request) {
        MemberParameterDTO.MemberSignupParamDto memberSignupParamDto = MemberDtoConverter.INSTANCE.toMemberSignupParamDto(request);
        Member newMember = memberService.MemberSignup(memberSignupParamDto);
        return ApiResponse.onSuccess(MemberConverter.toMemberSignupResultDto(newMember));
    }

    @PostMapping("/signin")
    @Operation(summary = "로그인 API", description = "회원 로그인 API, 이메일/패스워드 확인 후 토큰 반환")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1003", description = "틀린 이메일 또는 비밀번호입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ApiResponse<MemberResponseDTO.MemberSigninResponseDto> MemberSingin(@RequestBody @Valid MemberRequestDTO.MemberSigninRequestDto request) {
        MemberParameterDTO.MemberSigninParamDto memberSigninParamDto = MemberDtoConverter.INSTANCE.toMemberSigninParamDto(request);
        Map<Long, String> loginData = memberService.MemberSignin(memberSigninParamDto);
        return ApiResponse.onSuccess(MemberConverter.toMemberSigninResultDto(loginData));
    }

    @PatchMapping("/members/{memberId}")
    @Operation(summary = "회원 상태변경 API", description = "회원 상태변경 API, 회원 상태를 ACTIVE/INACTIVE 전환")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1004", description = "멤버를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ApiResponse<MemberResponseDTO.MemberChangeStatusResponseDto> MemberInactive(@PathVariable("memberId") Long memberId) {
        MemberParameterDTO.MemberChangeStatusParamDto memberChangeStatusParamDto = MemberDtoConverter.INSTANCE.toMemberChangeStatusParamDto(memberId);
        Member member = memberService.MemberInactive(memberChangeStatusParamDto);
        return ApiResponse.onSuccess(MemberConverter.toMemberChangeStatusDto(member));
    }
}
