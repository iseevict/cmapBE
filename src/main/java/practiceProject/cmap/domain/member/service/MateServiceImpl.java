package practiceProject.cmap.domain.member.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.member.dto.MateParameterDTO;
import practiceProject.cmap.domain.member.entity.Mate;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.repository.MateRepository;
import practiceProject.cmap.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MateServiceImpl implements MateService{

    private final MemberRepository memberRepository;
    private final MateRepository mateRepository;

    /**
     * Mate 설정 API
     * 반환 : Mate
     */
    @Override
    @Transactional
    public Mate MateCreate(@Valid MateParameterDTO.MateCreateParamDto param) {

        Member toMember = memberRepository.findById(param.getMateId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Member fromMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        mateRepository.findMateByMemberAndMate(fromMember, toMember)
                .ifPresent(c -> {
                    throw new CommonHandler(ErrorStatus._ALREADY_MATE);
                });


        Mate newMate = Mate.builder().build();
        newMate.setMemberAndMate(toMember, fromMember);
        return mateRepository.save(newMate);
    }

    /**
     * Mate 삭제 API
     * 반환 : Void
     */
    @Override
    @Transactional
    public void MateDelete(@Valid MateParameterDTO.MateDeleteParamDto param) {

        Member toMember = memberRepository.findById(param.getMateId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Member fromMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Mate findMate = mateRepository.findMateByMemberAndMate(fromMember, toMember)
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MATE_NOT_FOUND));

        mateRepository.delete(findMate);
    }
}
