package practiceProject.cmap.domain.comment.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.comment.entity.Comment;

public interface CommentCustomRepository {

    Slice<Comment> findAllForSliceByBoard(Board board, PageRequest pageRequest);
}
