package practiceProject.cmap.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practiceProject.cmap.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
}
