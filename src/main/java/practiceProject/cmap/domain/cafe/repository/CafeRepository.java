package practiceProject.cmap.domain.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.cafe.entity.Cafe;

import java.math.BigDecimal;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    Optional<Cafe> findAllByPosX(BigDecimal posX);
}