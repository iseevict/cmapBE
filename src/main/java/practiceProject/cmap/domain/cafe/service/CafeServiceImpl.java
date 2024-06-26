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
import practiceProject.cmap.domain.cafe.repository.CafeThemaRepository;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.repository.MemberRepository;
import practiceProject.cmap.domain.review.entity.Review;
import practiceProject.cmap.domain.review.repository.ReviewRepository;
import practiceProject.cmap.domain.thema.entity.Thema;
import practiceProject.cmap.domain.thema.repository.ThemaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeServiceImpl implements CafeService{

    private final CafeRepository cafeRepository;
    private final ThemaRepository themaRepository;
    private final MemberRepository memberRepository;
    private final CafeThemaRepository cafeThemaRepository;
    private final ReviewRepository reviewRepository;

    /**
     * 카페 생성 API
     * 반환 : Cafe
     */
    @Override
    @Transactional
    public Cafe CafeCreate(@Valid CafeParameterDTO.CafeCreateParamDto param) {

        // 멤버 데이터 확인
        Member cafeOwner = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Cafe newCafe = CafeConverter.toCafe(param, cafeOwner);

        // 위치 겹치는지 확인
        cafeRepository.findByPosXAndPosY(param.getPosX(), param.getPosY())
                .ifPresent(c -> {
                    throw new CommonHandler(ErrorStatus._CAFE_POS_EXIST);
                });

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

    /**
     * 카페 삭제 API
     * 반환 : Void
     */
    @Override
    @Transactional
    public void CafeDelete(@Valid CafeParameterDTO.CafeDeleteParamDto param) {

        Cafe findCafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        if (findCafe.getMember().equals(findMember)) {
            cafeRepository.delete(findCafe);
        }
        else {
            throw new CommonHandler(ErrorStatus._NOT_MEMBERS_CAFE);
        }
    }

    /**
     * 카페 수정 API
     * 반환 : Cafe
     */
    @Override
    @Transactional
    public Cafe CafeModify(@Valid CafeParameterDTO.CafeModifyParamDto param) {

        Cafe findCafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        // 위치 겹치는지 확인
        cafeRepository.findByPosXAndPosY(param.getPosX(), param.getPosY())
                .ifPresent(c -> {
                    if (!c.equals(findCafe)) {
                        throw new CommonHandler(ErrorStatus._CAFE_POS_EXIST);
                    }
                });

        // 원래 설정된 cafe - thema 연관관계 끊기
        List<CafeThema> findCafeThemaList = cafeThemaRepository.findAllByCafe(findCafe).get();
        cafeThemaRepository.deleteAll(findCafeThemaList);

        List<Thema> themaList = param.getThemaList().stream()
                .map(themaId -> themaRepository.findById(themaId)
                        .orElseThrow(() -> new CommonHandler(ErrorStatus._THEMA_NOT_FOUND))
                ).collect(Collectors.toList());

        List<CafeThema> cafeThemaList = CafeThemaConverter.toCafeThemaList(themaList);

        // Cafethema - Cafe 연결
        cafeThemaList.stream().forEach(cafeThema ->
                cafeThema.setCafe(findCafe));

        if (findCafe.getMember().equals(findMember)) {
            findCafe.modifyCafe(param.getName(), param.getIntroduce(), param.getPosX(), param.getPosY(), cafeThemaList);
            return findCafe;
        }
        else {
            throw new CommonHandler(ErrorStatus._NOT_MEMBERS_CAFE);
        }
    }

    /**
     * 지도 화면 API
     * 반환 : List<Cafe>
     */
    @Override
    public List<Cafe> CafeLocation(@Valid CafeParameterDTO.CafeLocationParamDto param) {

        List<Cafe> cafeList = cafeRepository.findAllByPosXAndPosY(param.getCenterX(), param.getCenterY(), param.getRadius());

        return cafeList;
    }

    /**
     * 지도 검색창 API
     * 반환 : List<Cafe>
     */
    @Override
    public List<Cafe> CafeSearch(@Valid CafeParameterDTO.CafeSearchParamDto param) {

        List<Cafe> cafeList = cafeRepository.findAllByPosXAndPosY(param.getCenterX(), param.getCenterY(), param.getRadius());

        return cafeList;
    }

    /**
     * 카페 정보 가져오기 API
     * 반환 : Cafe
     */
    @Override
        public Cafe CafeDetail(@Valid CafeParameterDTO.CafeDetailParamDto param) {

            Cafe findCafe = cafeRepository.findById(param.getCafeId())
                    .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        return findCafe;
    }

    /**
     * 카페 정보 가져오기 API
     * 반환 : List<CafeThema>
     */
    @Override
    public List<CafeThema> CafeDetailThema(Cafe cafe) {

        return cafeThemaRepository.findAllByCafe(cafe).orElse(new ArrayList<>());
    }

    /**
     * 랜덤 카페 가져오기 API
     * 반환 : Cafe
     */
    @Override
    public Cafe RandomCafe(@Valid CafeParameterDTO.RandomCafeParamDto param) {

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        List<Cafe> cafeList = cafeRepository.findRandomCafeByThema(findMember, param.getThemaList());

        if (cafeList.isEmpty()) throw new CommonHandler(ErrorStatus._RANDOM_CAFE_NOT_FOUND);

        Collections.shuffle(cafeList);

        return cafeList.get(0);
    }
}
