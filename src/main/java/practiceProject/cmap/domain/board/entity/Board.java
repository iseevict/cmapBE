package practiceProject.cmap.domain.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;
import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.comment.entity.Comments;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", updatable = false)
    private Cafe cafe;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardHashtag> boardHashtagList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<MemberLikeBoard> memberLikeBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comments> commentsList = new ArrayList<>();

    // 연관관계 편의 메서드
    public void setMember(Member member) {

        if (this.member != null) {
            this.member.getBoardList().remove(this);
        }
        this.member = member;
        this.member.getBoardList().add(this);
    }

    public void setCafe(Cafe cafe) {

        if (this.cafe != null) {
            this.cafe.getBoardList().remove(this);
        }
        this.cafe = cafe;
        this.cafe.getBoardList().add(this);
    }

    // 비즈니스 로직
    public void modifyBoard(String title, String body, List<BoardHashtag> boardHashtagList) {
        this.title = title;
        this.body = body;
        if (!this.boardHashtagList.isEmpty()) {
            this.boardHashtagList.clear();
        }
        this.boardHashtagList = boardHashtagList;
    }
}
