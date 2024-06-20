package practiceProject.cmap.domain.member.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.member.dto.MateParameterDTO;
import practiceProject.cmap.domain.member.entity.Mate;

import java.util.List;

public interface MateService {

    public Mate MateCreate(@Valid MateParameterDTO.MateCreateParamDto param);
    public void MateDelete(@Valid MateParameterDTO.MateDeleteParamDto param);
    public List<Mate> MateList(@Valid MateParameterDTO.MateListParamDto param);
}
