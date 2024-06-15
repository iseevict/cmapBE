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
    _MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER1004", "멤버를 찾지 못했습니다."),

    // 카페 관련
    _CAFE_POS_EXIST(HttpStatus.CONFLICT, "CAFE1001", "이미 카페가 존재하는 위치입니다."),
    _CAFE_NOT_FOUND(HttpStatus.NOT_FOUND, "CAFE1002", "카페를 찾지 못했습니다."),
    _NOT_MEMBERS_CAFE(HttpStatus.FORBIDDEN, "CAFE1003", "카페 주인이 아닙니다."),
    
    // 테마 관련
    _THEMA_NOT_FOUND(HttpStatus.NOT_FOUND, "THEMA1001", "테마를 찾을 수 없습니다."),

    // 리뷰 관련
    _OWNER_CANT_REVIEW(HttpStatus.FORBIDDEN, "REVIEW1001", "카페 주인은 자신의 카페에 리뷰를 달 수 없습니다."),
    _REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW1002", "리뷰를 찾지 못했습니다."),
    _REVIEW_CAFE_NOT_MATCHING(HttpStatus.BAD_REQUEST, "REVIEW1003", "해당 카페의 리뷰가 아닙니다."),

    // cmap 관련
    _ALREADY_CMAP(HttpStatus.CONFLICT, "CMAP1001", "이미 Cmap에 저장된 카페입니다."),
    _CMAP_NOT_FOUND(HttpStatus.NOT_FOUND, "CMAP1002", "Cmap을 찾지 못했습니다."),

    // hashtag 관련
    _HASHTAG_NOT_FOUND(HttpStatus.NOT_FOUND, "TAG1001", "해시태그를 찾지 못했습니다."),
    _FORBIDDEN_HASHTAG(HttpStatus.FORBIDDEN, "TAG1002", "권한이 없습니다."),

    // 게시판 관련
    _BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD1001", "게시글을 찾지 못했습니다."),
    _NOT_MEMBERS_BOARD(HttpStatus.FORBIDDEN, "BOARD1002", "게시글 작성자가 아닙니다."),
    _HEART_NOT_FOUND(HttpStatus.NOT_FOUND, "HEART1001", "이미 좋아요를 누르지 않은 상태입니다.");

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
