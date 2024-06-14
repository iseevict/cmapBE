package practiceProject.cmap.domain.cafe.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;

public interface CafeService {

    public Cafe CafeCreate(@Valid CafeParameterDTO.CafeCreateParamDto param);
    public void CafeDelete(@Valid CafeParameterDTO.CafeDeleteParamDto param);
}
