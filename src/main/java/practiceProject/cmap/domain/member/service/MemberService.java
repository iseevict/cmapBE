package practiceProject.cmap.domain.member.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.member.dto.MemberParameterDTO;
import practiceProject.cmap.domain.member.entity.Member;
import java.util.*;

public interface MemberService {

    public Member MemberSignup(@Valid MemberParameterDTO.MemberSignupParamDto param);
    public Map<Long, String> MemberSignin(@Valid MemberParameterDTO.MemberSigninParamDto param);
    public Member MemberChangeStatus(@Valid MemberParameterDTO.MemberChangeStatusParamDto param);
    public Member MemberChangeRole(@Valid MemberParameterDTO.MemberChangeRoleParamDto param);
}
