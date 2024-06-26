package practiceProject.cmap.domain.review.converter;

import org.springframework.data.domain.Page;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.review.dto.ReviewDataDTO;
import practiceProject.cmap.domain.review.dto.ReviewParameterDTO;
import practiceProject.cmap.domain.review.dto.ReviewResponseDTO;
import practiceProject.cmap.domain.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.MemberReviewListResponseDto toMemberReviewListResultDto(Page<Review> reviewPage) {

        List<ReviewDataDTO.ReviewDataDto> reviewDataDtoList = reviewPage.stream()
                .map(review ->
                        ReviewDataDTO.ReviewDataDto.builder()
                                .reviewId(review.getId())
                                .title(review.getTitle())
                                .body(review.getBody())
                                .updatedAt(review.getUpdatedAt())
                                .cafeId(review.getCafe().getId())
                                .cafeName(review.getCafe().getName())
                                .build())
                .collect(Collectors.toList());

        return ReviewResponseDTO.MemberReviewListResponseDto.builder()
                .reviewDataDtoList(reviewDataDtoList)
                .listSize(reviewDataDtoList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    public static ReviewResponseDTO.SingleReviewResponseDto toSingleReviewResultDto(Review review) {

        return ReviewResponseDTO.SingleReviewResponseDto.builder()
                .title(review.getTitle())
                .body(review.getBody())
                .score(review.getScore())
                .writer(review.getMember().getName())
                .memberId(review.getMember().getId())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewWriteResponseDto toReviewWriteResultDto(Review review) {

        return ReviewResponseDTO.ReviewWriteResponseDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ReviewResponseDTO.ReviewModifyResponseDto toReviewModifyResultDto(Review review) {

        return ReviewResponseDTO.ReviewModifyResponseDto.builder()
                .reviewId(review.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static ReviewResponseDTO.ReviewDeleteResponseDto toReviewDeleteResultDto() {

        return ReviewResponseDTO.ReviewDeleteResponseDto.builder()
                .message("삭제 성공입니다.")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewParameterDTO.ReviewWriteParamDto param) {

        return Review.builder()
                .title(param.getTitle())
                .body(param.getBody())
                .score(param.getScore())
                .build();
    }
}
