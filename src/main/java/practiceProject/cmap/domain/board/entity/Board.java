package practiceProject.cmap.domain.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;
import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;
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

    @Column(updatable = false, nullable = false)
    private String writer;

    @ColumnDefault("'0'")
    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 카페 추가해야 함
    // private Cafe cafe;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardHashtag> boardHashtagList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<MemberLikeBoard> memberLikeBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comments> commentsList = new ArrayList<>();
}
