package practiceProject.cmap.domain.review.controller;

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
import practiceProject.cmap.domain.review.converter.ReviewConverter;
import practiceProject.cmap.domain.review.converter.ReviewDtoConverter;
import practiceProject.cmap.domain.review.dto.ReviewParameterDTO;
import practiceProject.cmap.domain.review.dto.ReviewRequestDTO;
import practiceProject.cmap.domain.review.dto.ReviewResponseDTO;
import practiceProject.cmap.domain.review.entity.Review;
import practiceProject.cmap.domain.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cmap")
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping("/cafes/{cafeId}/reviews")
    @Operation(summary = "리뷰 작성 API", description = "리뷰 작성 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER1004", description = "멤버를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1002", description = "카페를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REVIEW1001", description = "카페 주인은 자신의 카페에 리뷰를 달 수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "cafeId", description = "카페 식별자, PathVariable")
    })
    public ApiResponse<ReviewResponseDTO.ReviewWriteResponseDto> WriteReview(@RequestBody @Valid ReviewRequestDTO.ReviewWriteRequestDto request, @PathVariable("cafeId") Long cafeId) {
        ReviewParameterDTO.ReviewWriteParamDto reviewWriteParamDto = ReviewDtoConverter.INSTANCE.toReviewWriteParamDto(request, cafeId);
        Review newReview = reviewService.ReviewWrite(reviewWriteParamDto);
        return ApiResponse.onSuccess(ReviewConverter.toReviewWriteResultDto(newReview));
    }
}
