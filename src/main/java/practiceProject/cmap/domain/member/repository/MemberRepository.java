package practiceProject.cmap.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.MemberStatus;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);
    Optional<List<Member>> findAllByStatus(MemberStatus status);
}
