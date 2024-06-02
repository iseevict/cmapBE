package practiceProject.cmap.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import practiceProject.cmap.config.apiCode.ApiResponseDTO;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private ErrorStatus status;

    public ApiResponseDTO getErrorReason() {
        return this.status.getResponse();
    }

    public ApiResponseDTO getErrorReasonHttpStatus() {
        return this.status.getResponseHttpStatus();
    }
}
