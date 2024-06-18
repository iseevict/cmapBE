package practiceProject.cmap.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;
import practiceProject.cmap.domain.hashtag.entity.Hashtag;

import java.util.List;
import java.util.Optional;

public interface BoardHashtagRepository extends JpaRepository<BoardHashtag, Long> {

    Optional<List<BoardHashtag>> findAllByBoard(Board board);
    Optional<List<BoardHashtag>> findAllByHashtag(Hashtag hashtag);
}
