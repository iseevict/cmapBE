package practiceProject.cmap.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.cmap.entity.Cmap;
import practiceProject.cmap.domain.comment.entity.Comments;
import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;
import practiceProject.cmap.domain.review.entity.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'USER'")
    private MemberRole role;

    private LocalDateTime inactiveAt;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Mate> mateList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberLikeBoard> memberLikeBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comments> commentsList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Cmap> cmapList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    // 연관관계 편의 메서드

    // 비즈니스 로직

    /**
     * 회원 상태 변경 메서드
     */
    public void changeStatus() {
        if (this.status == MemberStatus.ACTIVE) {
            this.status = MemberStatus.INACTIVE;
            this.inactiveAt = LocalDateTime.now();
        }
        else {
            this.status = MemberStatus.ACTIVE;
            this.inactiveAt = null;
        }
    }

    /**
     * 회원 역할 변경 메서드
     */
    public void changeRole() {
        if (this.role == MemberRole.USER) {
            this.role = MemberRole.MANAGER;
        }
        else {
            this.role = MemberRole.USER;
        }
    }
}
