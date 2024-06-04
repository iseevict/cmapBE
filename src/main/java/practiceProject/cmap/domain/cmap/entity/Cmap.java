package practiceProject.cmap.domain.cmap.entity;

import jakarta.persistence.*;
import lombok.*;
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
public class Cmap extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmap_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CmapStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
