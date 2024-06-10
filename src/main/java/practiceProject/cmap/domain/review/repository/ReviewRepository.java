package practiceProject.cmap.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
