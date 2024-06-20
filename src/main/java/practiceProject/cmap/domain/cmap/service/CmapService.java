package practiceProject.cmap.domain.cmap.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.cafe.dto.CafeParameterDTO;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cmap.dto.CmapDataDTO;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;
import practiceProject.cmap.domain.member.entity.Member;

import java.util.List;

public interface CmapService {

    public Cmap CmapCreate(@Valid CmapParameterDTO.CmapCreateParamDto param);
    public Cmap CmapStatusChange(@Valid CmapParameterDTO.CmapStatusChangeParamDto param);
    public void CmapDelete(@Valid CmapParameterDTO.CmapDeleteParamDto param);
    public CmapStatus CmapStatusCheck(@Valid CafeParameterDTO.CafeDetailParamDto param);
    public List<CmapDataDTO.CmapJoinCafeDataDto> CmapLocation(@Valid CmapParameterDTO.CmapLocationParamDto param);
}
