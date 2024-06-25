package practiceProject.cmap.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import practiceProject.cmap.config.BaseEntity.BaseTimeEntity;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Profile extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 254)
    private String introduce;

    @Column(length = 50)
    private String favoriteCafeTitle;

    @Lob
    private String favoriteCafeBody;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private ProfileImage profileImage;

    // 비즈니스 로직
    public void modifyProfile(String introduce, String favoriteCafeTitle, String favoriteCafeBody) {

        this.introduce = introduce;
        this.favoriteCafeTitle = favoriteCafeTitle;
        this.favoriteCafeBody = favoriteCafeBody;
    }

}
