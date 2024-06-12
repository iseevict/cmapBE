package practiceProject.cmap.domain.review.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.review.dto.ReviewParameterDTO;
import practiceProject.cmap.domain.review.entity.Review;

public interface ReviewService {

    public Review ReviewWrite(@Valid ReviewParameterDTO.ReviewWriteParamDto param);
    public Review ReviewModify(@Valid ReviewParameterDTO.ReviewModifyParamDto param);
}
