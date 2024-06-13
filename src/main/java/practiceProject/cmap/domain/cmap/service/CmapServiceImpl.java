package practiceProject.cmap.domain.cmap.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.repository.CafeRepository;
import practiceProject.cmap.domain.cmap.converter.CmapConverter;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.cmap.repository.CmapRepository;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CmapServiceImpl implements CmapService{

    private final CmapRepository cmapRepository;
    private final CafeRepository cafeRepository;
    private final MemberRepository memberRepository;

    /**
     * Cmap 추가 APi
     * 반환 : Cmap
     */
    @Override
    @Transactional
    public Cmap CmapCreate(@Valid CmapParameterDTO.CmapCreateParamDto param) {

        Cafe findCafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        cmapRepository.findByCafeAndMember(findCafe, findMember)
                .ifPresent(a -> {
                    throw new CommonHandler(ErrorStatus._ALREADY_CMAP);
                });

        Cmap newCmap = CmapConverter.toCmap(param);
        // 관계 연결
        newCmap.setCmap(findMember, findCafe);

        return cmapRepository.save(newCmap);
    }

    /**
     * Cmap 상태 수정 API
     * 반환 : Cmap
     */
    @Override
    @Transactional
    public Cmap CmapStatusChange(@Valid CmapParameterDTO.CmapStatusChangeParamDto param) {

        Cafe findCafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Cmap findCmap = cmapRepository.findByCafeAndMember(findCafe, findMember)
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CMAP_NOT_FOUND));

        // 상태 변경
        findCmap.setStatus(param.getStatus());

        return findCmap;
    }

    /**
     * Cmap 삭제 API
     * 반환 : Cmap
     */
    @Override
    @Transactional
    public void CmapDelete(@Valid CmapParameterDTO.CmapDeleteParamDto param) {

        Cafe findCafe = cafeRepository.findById(param.getCafeId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CAFE_NOT_FOUND));

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Cmap findCmap = cmapRepository.findByCafeAndMember(findCafe, findMember)
                .orElseThrow(() -> new CommonHandler(ErrorStatus._CMAP_NOT_FOUND));

        cmapRepository.delete(findCmap);
    }
}
