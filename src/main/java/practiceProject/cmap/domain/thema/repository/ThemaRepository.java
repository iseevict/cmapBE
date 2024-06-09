package practiceProject.cmap.domain.thema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.thema.entity.Thema;

public interface ThemaRepository extends JpaRepository<Thema, Long> {
}
