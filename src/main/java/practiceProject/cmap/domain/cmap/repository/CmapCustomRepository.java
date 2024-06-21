package practiceProject.cmap.domain.cmap.repository;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cmap.dto.CmapDataDTO;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;
import practiceProject.cmap.domain.member.entity.Member;

import java.math.BigDecimal;
import java.util.List;

public interface CmapCustomRepository {

    CmapStatus findCampStatusByCafeAndMember(Cafe cafe, Member member);
    List<CmapDataDTO.CmapJoinCafeDataDto> findCmapJoinCafe(@Valid CmapParameterDTO.CmapLocationParamDto param);
    List<Cmap> findAllCmapByMemberAndStatus (Member member);
}
