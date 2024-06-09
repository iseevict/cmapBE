package practiceProject.cmap.config.apiCode.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import practiceProject.cmap.config.apiCode.ApiResponseDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    // common error
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 멤버 관련
    _EMAIL_EXIST(HttpStatus.CONFLICT, "MEMBER1001", "이메일 중복입니다."),
    _NICKNAME_EXIST(HttpStatus.CONFLICT, "MEMBER1002", "닉네임 중복입니다."),
    _EMAIL_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER1003", "틀린 이메일 또는 비밀번호 입니다."),
    _PASSWORD_WRONG(HttpStatus.BAD_REQUEST, "MEMBER1003", "틀린 이메일 또는 비밀번호 입니다."),
    _MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER1004", "멤버를 찾지 못했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public ApiResponseDTO getResponse() {
        return ApiResponseDTO.builder()
                .code(this.code)
                .message(this.message)
                .isSuccess(false)
                .build();
    }

    public ApiResponseDTO getResponseHttpStatus() {
        return ApiResponseDTO.builder()
                .httpStatus(this.httpStatus)
                .code(this.code)
                .message(this.message)
                .isSuccess(false)
                .build();
    }
}
