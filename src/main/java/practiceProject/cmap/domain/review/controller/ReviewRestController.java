package practiceProject.cmap.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ApiResponse<ReviewResponseDTO.ReviewWriteResponseDto> ReviewWrite(@RequestBody @Valid ReviewRequestDTO.ReviewWriteRequestDto request, @PathVariable("cafeId") Long cafeId) {
        ReviewParameterDTO.ReviewWriteParamDto reviewWriteParamDto = ReviewDtoConverter.INSTANCE.toReviewWriteParamDto(request, cafeId);
        Review newReview = reviewService.ReviewWrite(reviewWriteParamDto);
        return ApiResponse.onSuccess(ReviewConverter.toReviewWriteResultDto(newReview));
    }

    @PatchMapping("/cafes/{cafeId}/reviews/{reviewId}")
    @Operation(summary = "리뷰 수정 API", description = "리뷰 수정 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REVIEW1003", description = "해당 카페의 리뷰가 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1002", description = "카페를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REVIEW1002", description = "리뷰를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "cafeId", description = "카페 식별자, PathVariable"),
            @Parameter(name = "reviewId", description = "리뷰 식별자, PathVariable")
    })
    public ApiResponse<ReviewResponseDTO.ReviewModifyResponseDto> ReviewModify(@RequestBody @Valid ReviewRequestDTO.ReviewModifyRequestDto request, @PathVariable("cafeId") Long cafeId, @PathVariable("reviewId") Long reviewId) {
        ReviewParameterDTO.ReviewModifyParamDto reviewModifyParamDto = ReviewDtoConverter.INSTANCE.toReviewModifyParamDto(request, cafeId, reviewId);
        Review review = reviewService.ReviewModify(reviewModifyParamDto);
        return ApiResponse.onSuccess(ReviewConverter.toReviewModifyResultDto(review));
    }

    @DeleteMapping("/cafes/{cafeId}/reviews/{reviewId}")
    @Operation(summary = "리뷰 삭제 API", description = "리뷰 삭제 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REVIEW1003", description = "해당 카페의 리뷰가 아닙니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CAFE1002", description = "카페를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REVIEW1002", description = "리뷰를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "cafeId", description = "카페 식별자, PathVariable"),
            @Parameter(name = "reviewId", description = "리뷰 식별자, PathVariable")
    })
    public ApiResponse<ReviewResponseDTO.ReviewDeleteResponseDto> ReviewDelete(@PathVariable("cafeId") Long cafeId, @PathVariable("reviewId") Long reviewId) {
        ReviewParameterDTO.ReviewDeleteParamDto reviewDeleteParamDto = ReviewDtoConverter.INSTANCE.toReviewDeleteParamDto(cafeId, reviewId);
        reviewService.ReviewDelete(reviewDeleteParamDto);
        return ApiResponse.onSuccess(ReviewConverter.toReviewDeleteResultDto());
    }

    @GetMapping("/cafes/{cafeId}/reviews/{reviewId}")
    @Operation(summary = "단일 리뷰 데이터 가져오기 API", description = "단일 리뷰 데이터 가져오기 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REVIEW1003", description = "해당 카페의 리뷰가 아닙니다.")
    })
    public ApiResponse<ReviewResponseDTO.SingleReviewResponseDto> SingleReviewData(@PathVariable("cafeId") Long cafeId, @PathVariable("reviewId") Long reviewId) {

        ReviewParameterDTO.SingleReviewParamDto singleReviewParamDto = ReviewDtoConverter.INSTANCE.toSingleReviewParamDto(cafeId, reviewId);
        Review review = reviewService.SingleReviewData(singleReviewParamDto);
        return ApiResponse.onSuccess(ReviewConverter.toSingleReviewResultDto(review));
    }

    @GetMapping("/reviews/{memberId}")
    @Operation(summary = "유저 리뷰 리스트 가져오기 API", description = "유저 리뷰 리스트 가져오기 API 입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공입니다.")
    })
    public ApiResponse<ReviewResponseDTO.MemberReviewListResponseDto> MemberReviewList(@RequestParam Integer page,
                                                                                       @RequestParam Integer size,
                                                                                       @PathVariable("memberId") Long memberId) {

        ReviewParameterDTO.MemberReviewListParamDto memberReviewListParamDto = ReviewDtoConverter.INSTANCE.toMemberReviewListParamDto(memberId, page, size);
        Page<Review> reviewPage = reviewService.MemberReviewList(memberReviewListParamDto);
        return ApiResponse.onSuccess(ReviewConverter.toMemberReviewListResultDto(reviewPage));
    }
}
