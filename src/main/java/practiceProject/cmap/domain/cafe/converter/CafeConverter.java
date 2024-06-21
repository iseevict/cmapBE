package practiceProject.cmap.domain.cafe.converter;

import org.springframework.data.domain.Slice;
import practiceProject.cmap.domain.cafe.dto.CafeDataDTO;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.dto.CafeResponseDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.entity.mapping.CafeThema;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.review.dto.ReviewDataDTO;
import practiceProject.cmap.domain.review.entity.Review;
import practiceProject.cmap.domain.thema.entity.Thema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CafeConverter {

    public static CafeResponseDTO.RandomCafeResponseDto toRandomCafeResultDto(Cafe cafe) {

        return CafeResponseDTO.RandomCafeResponseDto.builder()
                .cafeId(cafe.getId())
                .build();
    }

    public static CafeResponseDTO.CafeCreateResponseDto toCafeCreateResultDto (Cafe cafe) {

        return CafeResponseDTO.CafeCreateResponseDto.builder()
                .cafeId(cafe.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CafeResponseDTO.CafeDeleteResponseDto toCafeDeleteResultDto() {

        return CafeResponseDTO.CafeDeleteResponseDto.builder()
                .message("삭제 성공입니다.")
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static CafeResponseDTO.CafeModifyResponseDto toCafeModifyResultDto(Cafe cafe) {

        return CafeResponseDTO.CafeModifyResponseDto.builder()
                .cafeId(cafe.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CafeResponseDTO.CafeLocationResponseDto toCafeLocationResultDto(List<Cafe> cafeList) {

        List<CafeDataDTO.CafeLocationDataDto> cafeLocationDataDtoList = cafeList.stream()
                .map(cafe -> {
                    return CafeDataDTO.CafeLocationDataDto.builder()
                            .posX(cafe.getPosX())
                            .posY(cafe.getPosY())
                            .cafeId(cafe.getId())
                            .cafeName(cafe.getName())
                            .build();
                })
                .collect(Collectors.toList());

        return CafeResponseDTO.CafeLocationResponseDto.builder()
                .cafeLocationDataDtoList(cafeLocationDataDtoList)
                .totalCafeNum(cafeLocationDataDtoList.size())
                .build();
    }

    public static CafeResponseDTO.CafeSearchResponseDto toCafeSearchResultDto(List<Cafe> cafeList) {

        List<CafeDataDTO.CafeSearchDataDto> cafeSearchDataDtoList = cafeList.stream()
                .map(cafe -> {
                    return CafeDataDTO.CafeSearchDataDto.builder()
                            .cafeName(cafe.getName())
                            .introduce(cafe.getIntroduce())
                            .reviewNum(cafe.getReviewList().size())
                            .score(cafe.getScore())
                            .build();
                }).collect(Collectors.toList());

        return CafeResponseDTO.CafeSearchResponseDto.builder()
                .cafeSearchDataDtoList(cafeSearchDataDtoList)
                .totalCafeNum(cafeSearchDataDtoList.size())
                .build();
    }

    public static CafeResponseDTO.CafeDetailResponseDto toCafeDetailResultDto(Cafe cafe,
                                                                              Slice<Review> reviewSlice,
                                                                              List<CafeThema> cafeThemaList,
                                                                              CmapStatus status) {

        List<Long> themaList = cafeThemaList.stream()
                .map(cafeThema -> cafeThema.getThema().getId())
                .collect(Collectors.toList());

        List<ReviewDataDTO.ReviewPreviewDataDto> reviewPreviewDataDtoList = reviewSlice.stream()
                .map(review ->
                    ReviewDataDTO.ReviewPreviewDataDto.builder()
                            .body(review.getBody())
                            .writer(review.getMember().getName())
                            .memberId(review.getMember().getId())
                            .updatedAt(review.getUpdatedAt())
                            .build()
                ).collect(Collectors.toList());

        ReviewDataDTO.ReviewPreviewListDataDto reviewPreviewListDataDto = ReviewDataDTO.ReviewPreviewListDataDto
                .builder()
                .reviewPreviewDataDtoList(reviewPreviewDataDtoList)
                .hasNext(reviewSlice.hasNext())
                .pageNum(reviewSlice.getNumber())
                .pageSize(reviewSlice.getSize())
                .elementNum(reviewSlice.getNumberOfElements())
                .build();

        CafeDataDTO.CafeDetailDataDto cafeDetailDataDto = CafeDataDTO.CafeDetailDataDto.builder()
                .reviewPreviewListDataDto(reviewPreviewListDataDto)
                .cafeName(cafe.getName())
                .introduce(cafe.getIntroduce())
                .score(cafe.getScore())
                .boardNum(cafe.getBoardList().size())
                .reviewNum(cafe.getReviewList().size())
                .themaList(themaList)
                .status(status)
                .build();

        return CafeResponseDTO.CafeDetailResponseDto.builder()
                .cafeDetailDataDtoList(cafeDetailDataDto)
                .build();
    }

    public static Cafe toCafe (CafeParameterDTO.CafeCreateParamDto param, Member member) {

        return Cafe.builder()
                .name(param.getName())
                .posX(param.getPosX())
                .posY(param.getPosY())
                .introduce(param.getIntroduce())
                .member(member)
                .cafeThemaList(new ArrayList<>())
                .build();
    }
}
