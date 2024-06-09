package practiceProject.cmap.domain.cafe.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.cafe.converter.CafeConverter;
import practiceProject.cmap.domain.cafe.converter.CafeThemaConverter;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.entity.mapping.CafeThema;
import practiceProject.cmap.domain.cafe.repository.CafeRepository;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.repository.MemberRepository;
import practiceProject.cmap.domain.thema.entity.Thema;
import practiceProject.cmap.domain.thema.repository.ThemaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeServiceImpl implements CafeService{

    private final CafeRepository cafeRepository;
    private final ThemaRepository themaRepository;
    private final MemberRepository memberRepository;

    /**
     * 카페 생성 API
     * 반환 : Cafe
     */
    @Override
    @Transactional
    public Cafe CafeCreate(@Valid CafeParameterDTO.CafeCreateParamDTO param) {

        // 멤버 데이터 확인
        Member cafeOwner = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Cafe newCafe = CafeConverter.toCafe(param, cafeOwner);

        // 위치 겹치는지 확인
        List<Cafe> cafeList = cafeRepository.findAllByPosX(param.getPosX()).stream().toList();
        if (!cafeList.isEmpty()) {
            if(cafeList.stream().anyMatch(findCafe -> findCafe.getPosY().equals(param.getPosY()))) {
                throw new CommonHandler(ErrorStatus._CAFE_POS_EXIST);
            }
        }

        // 테마 리스트 가져오기
        List<Thema> themaList = param.getThemaList().stream()
                .map(themaId -> themaRepository.findById(themaId)
                        .orElseThrow(() -> new CommonHandler(ErrorStatus._THEMA_NOT_FOUND))
                ).collect(Collectors.toList());

        // CafeThema 생성
        List<CafeThema> cafeThemaList = CafeThemaConverter.toCafeThemaList(themaList);

        // Cafethema - Cafe 연결
        cafeThemaList.stream().forEach(cafeThema ->
                cafeThema.setCafe(newCafe));

        Cafe newSavedCafe = cafeRepository.save(newCafe);
        return newSavedCafe;
    }
}
