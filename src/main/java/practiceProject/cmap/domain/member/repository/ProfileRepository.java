package practiceProject.cmap.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByMember(Member member);
}
