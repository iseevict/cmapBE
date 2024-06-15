package practiceProject.cmap.domain.board.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.hashtag.entity.Hashtag;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardHashtag extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    // 연관관계 편의 메서드
    public void setBoard(Board board) {

        if (this.board != null) {
            this.board.getBoardHashtagList().remove(this);
        }
        this.board = board;
        this.board.getBoardHashtagList().add(this);
    }
}
