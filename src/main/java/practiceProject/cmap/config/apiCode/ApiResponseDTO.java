package practiceProject.cmap.config.apiCode;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@RequiredArgsConstructor
public class ApiResponseDTO {

    private HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final String code;
    private final String message;


}
