package practiceProject.cmap.domain.member.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.member.entity.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
public class MemberLikeBoard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 연관관게 편의 메서드
    public void setMemberAndBoard(Member member, Board board) {

        if (this.member != null) {
            this.member.getMemberLikeBoardList().remove(this);
        }
        if (this.board != null) {
            this.board.getMemberLikeBoardList().remove(this);
        }
        this.member = member;
        this.board = board;
        this.member.getMemberLikeBoardList().add(this);
        this.board.getMemberLikeBoardList().add(this);
    }
}
