package practiceProject.cmap.config.apiCode.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import practiceProject.cmap.config.ApiResponse;
import practiceProject.cmap.config.apiCode.ApiResponseDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus {

    _SUCCESS(HttpStatus.OK, "COMMON200", "성공입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public ApiResponseDTO getResponse() {
        return ApiResponseDTO.builder()
                .code(this.code)
                .message(this.message)
                .isSuccess(true)
                .build();
    }

    public ApiResponseDTO getResponseHttpStatus() {
        return ApiResponseDTO.builder()
                .httpStatus(this.httpStatus)
                .code(this.code)
                .message(this.message)
                .isSuccess(true)
                .build();
    }
}
