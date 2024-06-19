package practiceProject.cmap.domain.cmap.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;
import practiceProject.cmap.domain.cmap.entity.QCmap;
import practiceProject.cmap.domain.member.entity.Member;

@Repository
public class CmapCustomRepositoryImpl implements CmapCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CmapCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public CmapStatus findCampStatusByCafeAndMember(Cafe cafe, Member member) {

        QCmap cmap = QCmap.cmap;

        CmapStatus status = jpaQueryFactory
                .select(cmap.status)
                .from(cmap)
                .where(cmap.cafe.eq(cafe)
                        .and(cmap.member.eq(member)))
                .fetchOne();

        return status != null ? status : CmapStatus.NULL;
    }
}
