package practiceProject.cmap.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@DynamicInsert
@DynamicUpdate
public class Mate extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mate_id")
    private Member mate;

    // 연관관계 편의 메서드
    public void setMemberAndMate(Member toMember, Member fromMember) {

        if (this.member != null) {
            member.getMateList().remove(this);
        }
        this.member = fromMember;
        this.mate = toMember;
        this.member.getMateList().add(this);
    }
}
