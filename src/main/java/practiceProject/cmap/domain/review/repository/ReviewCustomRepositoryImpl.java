package practiceProject.cmap.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.review.entity.QReview;
import practiceProject.cmap.domain.review.entity.Review;

import java.util.List;

@Repository
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ReviewCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Slice<Review> findAllByCafeToSlice(Cafe cafe, Pageable pageable) {

        QReview review = QReview.review;

        List<Review> reviewList = jpaQueryFactory
                .selectFrom(review)
                .where(review.cafe.eq(cafe))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = reviewList.size() > pageable.getPageSize();

        if (hasNext) {
            reviewList.remove(reviewList.size() - 1);
        }

        return new SliceImpl<>(reviewList, pageable, hasNext);
    }
}
