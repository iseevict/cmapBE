package practiceProject.cmap.domain.board.entity;

import jakarta.persistence.*;
import lombok.*;
import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "hashtag", cascade = CascadeType.ALL)
    private List<BoardHashtag> boardHashtagList = new ArrayList<>();
}
