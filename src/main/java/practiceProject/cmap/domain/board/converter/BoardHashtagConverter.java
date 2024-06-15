package practiceProject.cmap.domain.board.converter;

import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;
import practiceProject.cmap.domain.hashtag.entity.Hashtag;

import java.util.List;
import java.util.stream.Collectors;

public class BoardHashtagConverter {

    public static List<BoardHashtag> toBoardHashtagList(List<Hashtag> hashtagList) {

        return hashtagList.stream()
                .map(hashtag ->
                        BoardHashtag.builder()
                                .hashtag(hashtag)
                                .build())
                .collect(Collectors.toList());
    }
}
