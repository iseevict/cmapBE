package practiceProject.cmap.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practiceProject.cmap.config.apiCode.status.ErrorStatus;
import practiceProject.cmap.config.exception.handler.CommonHandler;
import practiceProject.cmap.domain.member.dto.ProfileParameterDTO;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.Profile;
import practiceProject.cmap.domain.member.repository.MemberRepository;
import practiceProject.cmap.domain.member.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    /**
     * 프로필 수정 API
     * 반환 : Profile
     */
    @Override
    @Transactional
    public Profile ProfileModify(ProfileParameterDTO.ProfileModifyParamDto param) {

        Member findMember = memberRepository.findById(param.getMemberId())
                .orElseThrow(() -> new CommonHandler(ErrorStatus._MEMBER_NOT_FOUND));

        Profile findProfile = profileRepository.findByMember(findMember)
                .orElseThrow(() -> new CommonHandler(ErrorStatus._PROFILE_NOT_FOUND));

        findProfile.modifyProfile(param.getIntroduce(), param.getFavoriteCafeTitle(), param.getFavoriteCafeBody());
        return findProfile;
    }
}
