package practiceProject.cmap.domain.member.service;

import practiceProject.cmap.domain.member.dto.ProfileParameterDTO;
import practiceProject.cmap.domain.member.entity.Profile;

public interface ProfileService {

    public Profile ProfileModify(ProfileParameterDTO.ProfileModifyParamDto param);
}
