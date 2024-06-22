package practiceProject.cmap.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.comment.entity.Comment;
import practiceProject.cmap.domain.comment.entity.QComment;

import java.util.List;

@Repository
public class CommentCustomRepositoryImpl implements CommentCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public CommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Slice<Comment> findAllForSliceByBoard(Board board, PageRequest pageRequest) {

        QComment comment = QComment.comment;

        List<Comment> commentList = jpaQueryFactory
                .selectFrom(comment)
                .where(comment.board.eq(board))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize() + 1)
                .fetch();

        boolean hasNext = commentList.size() > pageRequest.getPageSize();

        if (hasNext) {
            commentList.remove(pageRequest.getPageSize() - 1);
        }

        return new SliceImpl<>(commentList, pageRequest, hasNext);
    }
}
