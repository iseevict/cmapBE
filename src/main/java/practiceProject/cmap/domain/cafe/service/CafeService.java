package practiceProject.cmap.domain.cafe.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;

import java.util.List;

public interface CafeService {

    public Cafe CafeCreate(@Valid CafeParameterDTO.CafeCreateParamDto param);
    public void CafeDelete(@Valid CafeParameterDTO.CafeDeleteParamDto param);
    public Cafe CafeModify(@Valid CafeParameterDTO.CafeModifyParamDto param);
    public List<Cafe> CafeLocation(@Valid CafeParameterDTO.CafeLocationParamDto param);
    public List<Cafe> CafeSearch(@Valid CafeParameterDTO.CafeSearchParamDto param);
}
