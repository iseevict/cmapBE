package practiceProject.cmap.domain.member.service;

import jakarta.validation.Valid;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    /**
     * 회원가입 API 메서드 (멤버, 프로필 엔티티 생성/저장)
     * 반환 : Member
     */
    @Override
    @Transactional
    public Member MemberSignup(@Valid MemberParameterDTO.MemberSignupParamDto param) {

        Member newMember = MemberConverter.toNewMember(param);
        // 닉네임 중복 확인
        if (memberRepository.findByName(newMember.getName()).isPresent()) {
            throw new CommonHandler(ErrorStatus._NICKNAME_EXIST);
        }
        // 이메일 중복 확인
        if (memberRepository.findByEmail(newMember.getEmail()).isPresent()) {
            throw new CommonHandler(ErrorStatus._EMAIL_EXIST);
        }

        Member newSavedMember = memberRepository.save(newMember); // 멤버 DB 저장
        profileRepository.save(ProfileConverter.toProfile(newSavedMember)); // 멤버 프로필 DB 저장

        return newSavedMember;
    }

    /**
     * 로그인 API
     * 반환 : Map<MemberId[Long], Token[String]>
     */
    @Override
    public Map<Long, String> MemberSignin(@Valid MemberParameterDTO.MemberSigninParamDto param) {

        // 이메일 이용해서 멤버 찾기
        Member findMember = memberRepository.findByEmail(param.getEmail())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._EMAIL_NOT_EXIST));

        if (findMember.getPassword().equals(param.getPassword())) {
            return Collections.singletonMap(findMember.getId(), "cmapToken" + findMember.getName());
        }
        else {
            throw new CommonHandler(ErrorStatus._PASSWORD_WRONG);
        }
    }

    /**
     * 회원 탈퇴 API
     * 반환 : Member
     */
    @Override
    @Transactional
    public Member MemberInactive (@Valid MemberParameterDTO.MemberChangeStatusParamDto param) {

        // 멤버 찾기
        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));


        findMember.changeStatus();

        return findMember;
    }
}
