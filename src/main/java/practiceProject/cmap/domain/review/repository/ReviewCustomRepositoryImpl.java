package practiceProject.cmap.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.review.entity.QReview;
import practiceProject.cmap.domain.review.entity.Review;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<Review> findAllForPageByMember(Member member, PageRequest pageRequest) {

        QReview review = QReview.review;

        List<Review> reviewList = jpaQueryFactory
                .selectFrom(review)
                .where(review.member.eq(member))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(jpaQueryFactory
                .select(review.count())
                .from(review)
                .where(review.member.eq(member))
                .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(reviewList, pageRequest, total);
    }
}
