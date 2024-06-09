package practiceProject.cmap.domain.cafe.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;
import practiceProject.cmap.domain.cafe.entity.Cafe;
import practiceProject.cmap.domain.thema.entity.Thema;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeThema extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thema_id")
    private Thema thema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    // 연관관계 편의 메서드

    public void setCafe(Cafe cafe) {
        if (this.cafe != null) {
            this.cafe.getCafeThemaList().remove(this);
        }
        this.cafe = cafe;
        cafe.getCafeThemaList().add(this);
    }
}
