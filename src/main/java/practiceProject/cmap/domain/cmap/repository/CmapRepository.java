package practiceProject.cmap.domain.cmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.member.entity.Member;

import java.util.Optional;

public interface CmapRepository extends JpaRepository<Cmap, Long> {
    Optional<Cmap> findByCafeAndMember(Cafe cafe, Member member);
}
