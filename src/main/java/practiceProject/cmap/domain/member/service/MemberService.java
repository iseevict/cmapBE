package practiceProject.cmap.domain.member.service;

import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.dto.MemberRequestDTO;
import practiceProject.cmap.domain.member.entity.Member;

public interface MemberService {

    public Member MemberSignup(MemberParameterDTO.MemberSignupParamDto param);
}
