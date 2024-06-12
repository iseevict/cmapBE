package practiceProject.cmap.domain.review.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.review.dto.ReviewParameterDTO;
import practiceProject.cmap.domain.review.dto.ReviewRequestDTO;

@Mapper
public interface ReviewDtoConverter {

    ReviewDtoConverter INSTANCE = Mappers.getMapper(ReviewDtoConverter.class);

    ReviewParameterDTO.ReviewWriteParamDto toReviewWriteParamDto(ReviewRequestDTO.ReviewWriteRequestDto request, Long cafeId);
    ReviewParameterDTO.ReviewModifyParamDto toReviewModifyParamDto(ReviewRequestDTO.ReviewModifyRequestDto request, Long cafeId, Long reviewId);
    ReviewParameterDTO.ReviewDeleteParamDto toReviewDeleteParamDto(Long cafeId, Long reviewId);
}
