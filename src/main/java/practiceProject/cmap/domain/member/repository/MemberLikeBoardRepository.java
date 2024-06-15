package practiceProject.cmap.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;

import java.util.Optional;

public interface MemberLikeBoardRepository extends JpaRepository<MemberLikeBoard, Long> {

    Optional<MemberLikeBoard> findByMemberAndBoard(Member member, Board board);
}
