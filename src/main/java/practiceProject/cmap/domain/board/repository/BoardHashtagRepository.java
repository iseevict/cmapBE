package practiceProject.cmap.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;

public interface BoardHashtagRepository extends JpaRepository<BoardHashtag, Long> {
}
