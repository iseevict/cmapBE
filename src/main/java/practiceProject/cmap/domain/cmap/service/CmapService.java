package practiceProject.cmap.domain.cmap.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.entity.Cmap;

public interface CmapService {

    public Cmap CmapCreate(@Valid CmapParameterDTO.CmapCreateParamDto param);
    public Cmap CmapStatusChange(@Valid CmapParameterDTO.CmapStatusChangeParamDto param);
}
