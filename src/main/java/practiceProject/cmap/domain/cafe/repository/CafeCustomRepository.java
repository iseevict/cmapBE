package practiceProject.cmap.domain.cafe.repository;

import practiceProject.cmap.domain.cafe.entity.Cafe;

import java.math.BigDecimal;
import java.util.List;

public interface CafeCustomRepository {

    List<Cafe> findAllByPosXAndPosY(BigDecimal centerX, BigDecimal centerY, BigDecimal radius);
    Cafe findWithReviewAndThema(Long cafeId);

}
