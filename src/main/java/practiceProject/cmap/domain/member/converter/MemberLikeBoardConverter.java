package practiceProject.cmap.domain.member.converter;

import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;

public class MemberLikeBoardConverter {

    public static MemberLikeBoard toMemberLikeBoard() {

        return MemberLikeBoard.builder().build();
    }
}
