package practiceProject.cmap.domain.member.converter;

import practiceProject.cmap.domain.member.entity.Member;
import practiceProject.cmap.domain.member.entity.Profile;

public class ProfileConverter {

    public static Profile toProfile (Member member) {
        return Profile.builder()
                .member(member)
                .build();
    }
}
