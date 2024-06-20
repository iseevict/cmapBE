package practiceProject.cmap.domain.member.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.member.dto.MateParameterDTO;
import practiceProject.cmap.domain.member.entity.Mate;

public interface MateService {

    public Mate MateCreate(@Valid MateParameterDTO.MateCreateParamDto param);
}
