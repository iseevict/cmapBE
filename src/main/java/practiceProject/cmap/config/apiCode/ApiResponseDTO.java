package practiceProject.cmap.config.apiCode;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponseDTO {

    private HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final String code;
    private final String message;


}
