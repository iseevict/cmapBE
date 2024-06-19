package practiceProject.cmap.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {
    List<Review> findAllByCafe(Cafe cafe);
}
