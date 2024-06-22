package practiceProject.cmap.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import practiceProject.cmap.domain.board.dto.BoardDataDTO;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.member.entity.Member;

import java.util.List;

public interface BoardCustomRepository {

    Page<Board> findAllForPage(PageRequest pageRequest);
    List<Long> findAllTagByBoard(Board board);
    Page<Board> findAllForPageByTag(PageRequest pageRequest, List<Long> tagList);
}
