package practiceProject.cmap.domain.review.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.repository.CafeRepository;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.repository.MemberRepository;
import practiceProject.cmap.domain.review.converter.ReviewConverter;
import practiceProject.cmap.domain.review.dto.ReviewParameterDTO;
import practiceProject.cmap.domain.review.entity.Review;
import practiceProject.cmap.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final CafeRepository cafeRepository;

    /**
     * 리뷰 작성 API
     * 반환 : Review
     */
    @Override
    @Transactional
    public Review ReviewWrite(@Valid ReviewParameterDTO.ReviewWriteParamDto param) {

        // 멤버 찾기
        Member writer = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        // 카페 찾기
        Cafe findCafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        // 주인은 자신의 카페에 리뷰 작성 못 함
        if (findCafe.getMember().equals(writer)) throw new CommonHandler(ErrorStatus._OWNER_CANT_REVIEW);

        // 리뷰 엔티티 생성, 관계 맺기
        Review newReview = ReviewConverter.toReview(param);
        newReview.setCafe(findCafe);
        newReview.setMember(writer);

        return reviewRepository.save(newReview);
    }

    /**
     * 리뷰 수정 API
     * 반환 : Review
     */
    @Override
    @Transactional
    public Review ReviewModify(@Valid ReviewParameterDTO.ReviewModifyParamDto param) {

        // 카페 데이터 찾기
        Cafe cafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        // 리뷰 데이터 찾기
        Review findReview = reviewRepository.findById(param.getReviewId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._REVIEW_NOT_FOUND));

        // 카페 : 리뷰 검사
        if (!findReview.getCafe().equals(cafe)) {
            throw new CommonHandler(ErrorStatus._REVIEW_CAFE_NOT_MATCHING);
        }

        // 수정
        findReview.modifyReview(param.getTitle(), param.getBody(), param.getScore());

        return findReview;
    }

    /**
     * 리뷰 삭제 API
     * 반환 : void
     */
    @Override
    @Transactional
    public void ReviewDelete(@Valid ReviewParameterDTO.ReviewDeleteParamDto param) {

        // 카페 데이터 찾기
        Cafe cafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        // 리뷰 데이터 찾기
        Review findReview = reviewRepository.findById(param.getReviewId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._REVIEW_NOT_FOUND));

        // 카페 : 리뷰 검사
        if (!findReview.getCafe().equals(cafe)) {
            throw new CommonHandler(ErrorStatus._REVIEW_CAFE_NOT_MATCHING);
        }

        findReview.deleteReview();

        // 리뷰 삭제
        reviewRepository.delete(findReview);

    }
}
