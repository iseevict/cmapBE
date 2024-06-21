package practiceProject.cmap.domain.cafe.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.entity.QCafe;
import practiceProject.cmap.domain.cafe.entity.mapping.QCafeThema;
import practiceProject.cmap.domain.cmap.entity.QCmap;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.review.entity.QReview;
import practiceProject.cmap.domain.thema.entity.QThema;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<Cafe> findRandomCafeByThema(Member member, List<Long> themaList) {

        QCafe cafe = QCafe.cafe;
        QCafeThema cafeThema = QCafeThema.cafeThema;
        QCmap cmap = QCmap.cmap;

        BooleanBuilder builder = new BooleanBuilder();

        if (!themaList.isEmpty()) {

            builder.and(cafeThema.cafe.id.in(
                    JPAExpressions
                            .select(cafeThema.cafe.id)
                            .from(cafeThema)
                            .groupBy(cafeThema.cafe.id)
                            .having(cafeThema.cafe.id.count().eq(Expressions.constant(themaList.size())))
                            .where(cafeThema.thema.id.in(themaList))
            ));
        }

        List<Long> cafeIdList = jpaQueryFactory
                .select(cmap.cafe.id)
                .from(cmap)
                .where(cmap.member.eq(member))
                .fetch();

        List<Cafe> cafeList = jpaQueryFactory
                .select(cafeThema.cafe)
                .from(cafeThema)
                .where(builder
                        .and(cafeThema.cafe.id.notIn(cafeIdList)))
                .fetch();

        return cafeList != null ? cafeList : new ArrayList<>();
    }
}
