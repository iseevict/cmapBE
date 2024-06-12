package practiceProject.cmap.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.member.entity.Member;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;

    @ColumnDefault("'0'")
    private Float score;

    @Column(nullable = false, updatable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", updatable = false)
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    // 연관관계 편의 메서드
    public void setCafe(Cafe cafe) {
        if (this.cafe != null) {
            this.cafe.getReviewList().remove(this);
        }
        this.cafe = cafe;
        this.cafe.getReviewList().add(this);

        // 카페 리뷰 수 처리 로직
        this.cafe.updateReviewNum();

        // 카페 평점 처리 로직
        this.cafe.updateScore();
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getReviewList().remove(this);
        }
        this.member = member;
        this.writer = member.getName();
        this.member.getReviewList().add(this);
        this.member.updateReviewNum();
    }

    // 비즈니스 로직
    public void modifyReview(String title, String body, Float score) {
        this.title = title;
        this.body = body;
        this.score = score;
        // 수정한 평점 적용
        this.cafe.updateScore();
    }
}
