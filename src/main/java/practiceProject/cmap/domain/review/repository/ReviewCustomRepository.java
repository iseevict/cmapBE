package practiceProject.cmap.domain.review.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.review.entity.Review;

public interface ReviewCustomRepository {

    Slice<Review> findAllByCafeToSlice(Cafe cafe, Pageable pageable);
}
