package practiceProject.cmap.domain.cmap.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.cafe.entity.QCafe;
import practiceProject.cmap.domain.cmap.dto.CmapDataDTO;
import practiceProject.cmap.domain.cmap.dto.CmapParameterDTO;
import practiceProject.cmap.domain.cmap.dto.QCmapDataDTO_CmapJoinCafeDataDto;
import practiceProject.cmap.domain.cmap.entity.CmapStatus;
import practiceProject.cmap.domain.cmap.entity.QCmap;
import practiceProject.cmap.domain.member.entity.Member;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public List<CmapDataDTO.CmapJoinCafeDataDto> findCmapJoinCafe(@Valid CmapParameterDTO.CmapLocationParamDto param) {

        BigDecimal lowPosX = param.getCenterX().subtract(param.getRadius());
        BigDecimal highPosX = param.getCenterX().add(param.getRadius());
        BigDecimal lowPosY = param.getCenterY().subtract(param.getRadius());
        BigDecimal highPosY = param.getCenterY().add(param.getRadius());

        QCmap cmap = QCmap.cmap;
        QCafe cafe = QCafe.cafe;

        return jpaQueryFactory
                .select(new QCmapDataDTO_CmapJoinCafeDataDto(
                        cafe.posX,
                        cafe.posY,
                        cafe.name,
                        cmap.status,
                        cafe.id
                ))
                .from(cmap)
                .leftJoin(cmap.cafe, cafe)
                .where(cafe.posX.between(lowPosX, highPosX)
                        .and(cafe.posY.between(lowPosY, highPosY))
                        .and(cmap.member.id.eq(param.getMemberId())))
                .fetch();
    }
}
