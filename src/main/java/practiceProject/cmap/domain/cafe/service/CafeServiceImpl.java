package practiceProject.cmap.domain.cafe.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.cafe.converter.CafeConverter;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.repository.CafeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeServiceImpl implements CafeService{

    private final CafeRepository cafeRepository;

    /**
     * 카페 생성 API
     * 반환 : Cafe
     */
    @Override
    @Transactional
    public Cafe CafeCreate(@Valid CafeParameterDTO.CafeCreateParamDTO param) {

        Cafe newCafe = CafeConverter.toCafe(param);

        // 위치 겹치는지 확인
        List<Cafe> cafeList = cafeRepository.findAllByPosX(param.getPosX()).stream().toList();
        if (!cafeList.isEmpty()) {
            if(cafeList.stream().anyMatch(findCafe -> findCafe.getPosY().equals(param.getPosY()))) {
                throw new CommonHandler(ErrorStatus._CAFE_POS_EXIST);
            }
        }

        Cafe newSavedCafe = cafeRepository.save(newCafe);
        return newSavedCafe;
    }
}
