package practiceProject.cmap.domain.cmap.repository;

import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;
import practiceProject.cmap.domain.member.entity.Member;

public interface CmapCustomRepository {

    CmapStatus findCampStatusByCafeAndMember(Cafe cafe, Member member);
}
