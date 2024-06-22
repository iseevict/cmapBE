package practiceProject.cmap.domain.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.entity.QBoard;
import practiceProject.cmap.domain.board.entity.mapping.QBoardHashtag;
import practiceProject.cmap.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardCustomRepositoryImpl implements BoardCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public BoardCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Board> findAllForPage(PageRequest pageRequest) {

        QBoard board = QBoard.board;

        List<Board> results =
                jpaQueryFactory
                        .selectFrom(board)
                        .offset(pageRequest.getOffset())
                        .limit(pageRequest.getPageSize())
                        .fetch();

        Long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(board.count())
                        .from(board)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(results, pageRequest, total);
    }

    @Override
    public List<Long> findAllTagByBoard(Board board) {

        QBoardHashtag boardHashtag = QBoardHashtag.boardHashtag;

        List<Long> tagList =  jpaQueryFactory
                .select(boardHashtag.hashtag.id)
                .from(boardHashtag)
                .where(boardHashtag.board.eq(board))
                .fetch();

        return tagList != null ? tagList : new ArrayList<>();
    }

    @Override
    public Page<Board> findAllForPageByTag(PageRequest pageRequest, List<Long> tagList) {

        QBoard board = QBoard.board;
        QBoardHashtag boardHashtag = QBoardHashtag.boardHashtag;
        BooleanBuilder builder = new BooleanBuilder();

        if (!tagList.isEmpty()) {

            builder.and(board.id.in(
                    JPAExpressions
                            .select(boardHashtag.board.id)
                            .from(boardHashtag)
                            .groupBy(boardHashtag.board.id)
                            .having(boardHashtag.board.id.count().eq(Expressions.constant(tagList.size())))
                            .where(boardHashtag.hashtag.id.in(tagList))
            ));
        }

        List<Board> boardList = jpaQueryFactory
                .selectFrom(board)
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .where(builder)
                .fetch();

        Long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(board.count())
                        .from(board)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(boardList, pageRequest, total);
    }
}
