package practiceProject.cmap.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import practiceProject.cmap.domain.member.entity.Mate;
import practiceProject.cmap.domain.member.entity.Member;

import java.util.Optional;

public interface MateRepository extends JpaRepository<Mate, Long> {

    @Query("select m from Mate m where m.member = :member AND m.mate = :mate")
    Optional<Mate> findMateByMemberAndMate(Member member, Member mate);
}
