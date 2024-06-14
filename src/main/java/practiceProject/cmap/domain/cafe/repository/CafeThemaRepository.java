package practiceProject.cmap.domain.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.entity.mapping.CafeThema;

import java.util.List;
import java.util.Optional;

public interface CafeThemaRepository extends JpaRepository<CafeThema, Long> {
    Optional<List<CafeThema>> findAllByCafe(Cafe cafe);
}
