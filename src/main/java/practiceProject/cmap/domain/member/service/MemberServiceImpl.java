package practiceProject.cmap.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.member.converter.MemberConverter;
import practiceProject.cmap.domain.member.converter.ProfileConverter;
import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.repository.MemberRepository;
import practiceProject.cmap.domain.member.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    /**
     * 회원가입 API 메서드 (멤버, 프로필 엔티티 생성/저장)
     */
    @Override
    @Transactional
    public Member MemberSignup(MemberParameterDTO.MemberSignupParamDto param) {

        Member newSavedMember = MemberConverter.toNewMember(param);
        // 닉네임 중복 확인
        if (memberRepository.findByName(newSavedMember.getName()).isPresent()) {
            throw new CommonHandler(ErrorStatus._NICKNAME_EXIST);
        }
        // 이메일 중복 확인
        if (memberRepository.findByEmail(newSavedMember.getEmail()).isPresent()) {
            throw new CommonHandler(ErrorStatus._EMAIL_EXIST);
        }

        memberRepository.save(newSavedMember); // 멤버 DB 저장
        profileRepository.save(ProfileConverter.toProfile(newSavedMember)); // 멤버 프로필 DB 저장

        return newSavedMember;
    }

}
