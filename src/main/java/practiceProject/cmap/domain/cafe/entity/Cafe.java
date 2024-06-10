package practiceProject.cmap.domain.cafe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.cafe.entity.mapping.CafeThema;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.review.entity.Review;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Cafe extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ColumnDefault("'0'")
    private Float score;

    @ColumnDefault("'0'")
    private Integer boardNum;

    @ColumnDefault("'0'")
    private Integer reviewNum;

    @Column(length = 50)
    private String introduce;

    @Column(precision = 13, scale = 10, nullable = false)
    private BigDecimal posX;

    @Column(precision = 13, scale = 10, nullable = false)
    private BigDecimal posY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<CafeThema> cafeThemaList = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Cmap> cmapList = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    // 연관관계 편의 메서드

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getCafeList().remove(this);
        }
        this.member = member;
        member.getCafeList().add(this);
    }

    // 비즈니스 로직
    /**
     * 리뷰 생성 시 카페 리뷰 수 추가
     */
    public void updateReviewNum() {
        reviewNum = reviewList.size();
    }

    /**
     * 리뷰 생성 시 별점 갱신
     */
    public void updateScore(Float updateScore) {
        this.score = updateScore;
    }
}
