package practiceProject.cmap.domain.cmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.cmap.entity.Cmap;

public interface CmapRepository extends JpaRepository<Cmap, Long> {
}
