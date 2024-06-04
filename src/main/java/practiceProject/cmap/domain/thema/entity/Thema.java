package practiceProject.cmap.domain.thema.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import practiceProject.cmap.domain.cafe.entity.mapping.CafeThema;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Thema {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thema_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "thema", cascade = CascadeType.ALL)
    private List<CafeThema> cafeThemaList = new ArrayList<>();
}
