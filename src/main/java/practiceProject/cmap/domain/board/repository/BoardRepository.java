package practiceProject.cmap.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
