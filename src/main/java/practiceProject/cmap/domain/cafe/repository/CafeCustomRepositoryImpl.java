package practiceProject.cmap.domain.cafe.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.entity.QCafe;
import practiceProject.cmap.domain.cafe.entity.mapping.QCafeThema;
import practiceProject.cmap.domain.review.entity.QReview;
import practiceProject.cmap.domain.thema.entity.QThema;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CafeCustomRepositoryImpl implements CafeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CafeCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Cafe> findAllByPosXAndPosY(BigDecimal centerX, BigDecimal centerY, BigDecimal radius) {

        BigDecimal lowPosX = centerX.subtract(radius);
        BigDecimal highPosX = centerX.add(radius);
        BigDecimal lowPosY = centerY.subtract(radius);
        BigDecimal highPosY = centerY.add(radius);

        QCafe cafe = QCafe.cafe;

        return jpaQueryFactory
                .select(cafe)
                .from(cafe)
                .where(cafe.posX.between(lowPosX, highPosX)
                        .and(cafe.posY.between(lowPosY, highPosY)))
                .fetch();
    }

    @Override
    public Cafe findWithReviewAndThema(Long cafeId) {

        QCafe cafe = QCafe.cafe;
        QReview review = QReview.review;
        QCafeThema cafeThema = QCafeThema.cafeThema;
        QThema thema = QThema.thema;

        return jpaQueryFactory
                .selectFrom(cafe)
                .leftJoin(cafe.reviewList, review).fetchJoin()
                .where(cafe.id.eq(cafeId))
                .fetchOne();
    }
}
