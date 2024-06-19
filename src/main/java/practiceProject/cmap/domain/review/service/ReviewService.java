package practiceProject.cmap.domain.review.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.review.dto.ReviewParameterDTO;
import practiceProject.cmap.domain.review.entity.Review;

import java.util.List;

public interface ReviewService {

    public Review ReviewWrite(@Valid ReviewParameterDTO.ReviewWriteParamDto param);
    public Review ReviewModify(@Valid ReviewParameterDTO.ReviewModifyParamDto param);
    public void ReviewDelete(@Valid ReviewParameterDTO.ReviewDeleteParamDto param);
    public Slice<Review> CafeDetailReviewList(Cafe cafe, Pageable pageable);
}
