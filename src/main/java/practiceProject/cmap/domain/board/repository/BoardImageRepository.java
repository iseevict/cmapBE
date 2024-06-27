package practiceProject.cmap.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.board.entity.BoardImage;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
}
