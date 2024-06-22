package practiceProject.cmap.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.review.entity.Review;

public interface ReviewCustomRepository {

    Slice<Review> findAllByCafeToSlice(Cafe cafe, Pageable pageable);
    Page<Review> findAllForPageByMember(Member member, PageRequest pageRequest);
}
