package practiceProject.cmap.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.member.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
