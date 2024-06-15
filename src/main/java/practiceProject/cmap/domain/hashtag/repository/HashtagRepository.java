package practiceProject.cmap.domain.hashtag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.hashtag.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
}
